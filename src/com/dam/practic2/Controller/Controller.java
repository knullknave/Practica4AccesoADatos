package com.dam.practic2.Controller;

import com.dam.practic2.Model.Methods.Methods;
import com.dam.practic2.View.JConnection;
import com.dam.practic2.View.Window;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.Date;

/**
 * @author Daniel Cano Mainar
 * @version 1.0 10/11/2015
 * Esta clase controla lo que le pueda pasar a la ventana. Incorpora modelo MVC (Model, view, controller)
 * En esta clase se controlan todos los eventos del programa as� como tratamiento con cada uno de los elementos de la ventana.
 */
public class Controller implements ActionListener
{
    private Window window;
    private Methods methods;
    private JConnection c;
    private DefaultTableModel ColumnMedicTable;
    private DefaultTableModel ColumnPatientTable;
    private DefaultTableModel ColumnEpisodeTable;
    private DefaultTableModel ColumnAnalysisTable;
    private DefaultTableModel ColumnRadiographyTable;
    private DefaultTableModel ColumnPharmacotherapyTable;
    public int idMedic;
    public int idPatient;
    public int idPatient2;
    //TODO CREAR EL RESTO DE ID'S

    /**
     * Este es el constructor de la clase. Aqui se implementan todos los listener de la ventana
     * @param w
     */
    public Controller(Window w)
    {
        this.window = w;
        this.methods = new Methods(Controller.this);

        this.window.jbNewP.addActionListener(this);
        this.window.jbModP.addActionListener(this);
        this.window.jbSaveP.addActionListener(this);
        this.window.jbDelP.addActionListener(this);
        this.window.jbEnter.addActionListener(this);
        this.window.jbRegister.addActionListener(this);
        this.window.jbAddE.addActionListener(this);
        this.window.jbModE.addActionListener(this);
        this.window.jbDelE.addActionListener(this);
        this.window.jbAddA.addActionListener(this);
        this.window.jbModA.addActionListener(this);
        this.window.jbDelA.addActionListener(this);
        this.window.jbAddR.addActionListener(this);
        this.window.jbModR.addActionListener(this);
        this.window.jbDelR.addActionListener(this);
        this.window.jbAddPh.addActionListener(this);
        this.window.jbModPh.addActionListener(this);
        this.window.jbDelPh.addActionListener(this);
        this.window.jbModM.addActionListener(this);
        this.window.jbDelM.addActionListener(this);
        this.window.jbCancel.addActionListener(this);

        this.window.menuItem.addActionListener(this);
        this.window.menuItem2.addActionListener(this);
        this.window.menuItem3.addActionListener(this);
        this.window.menuItem4.addActionListener(this);
        this.window.menuItem5.addActionListener(this);
        this.window.menuItem6.addActionListener(this);
        this.window.menuItem7.addActionListener(this);
        this.window.menuItem8.addActionListener(this);

        ColumnMedicTable = new DefaultTableModel();
        window.tableMedic.setModel(ColumnMedicTable);
        ColumnPatientTable = new DefaultTableModel();
        window.tablePatient1.setModel(ColumnPatientTable);
        window.tablePatient2.setModel(ColumnPatientTable);
        window.tablePatient3.setModel(ColumnPatientTable);
        window.tablePatient4.setModel(ColumnPatientTable);
        window.tablePatient5.setModel(ColumnPatientTable);
        ColumnEpisodeTable = new DefaultTableModel();
        window.tableEpisodes.setModel(ColumnEpisodeTable);
        ColumnAnalysisTable = new DefaultTableModel();
        window.tableAnalysis.setModel(ColumnAnalysisTable);
        ColumnRadiographyTable = new DefaultTableModel();
        window.tableRadiography.setModel(ColumnRadiographyTable);
        ColumnPharmacotherapyTable = new DefaultTableModel();
        window.tablePharmacotherapy.setModel(ColumnPharmacotherapyTable);

        ColumnNames();
        loadMedic();
        disconect();
        dishableMedic();

        window.tableMedic.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                window.tablePatient2.clearSelection();
                window.tablePatient1.clearSelection();
                if (window.tableMedic.isRowSelected(window.tableMedic.getSelectedRow()))
                {
                    idMedic = Integer.parseInt(window.tableMedic.getValueAt(window.tableMedic.getSelectedRow(), 0).toString());
                    enableMedic();
                }
            }
        });

        window.tablePatient1.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                window.tablePatient2.clearSelection();
                window.tableMedic.clearSelection();
                if (window.tablePatient1.isRowSelected(window.tablePatient1.getSelectedRow()))
                {
                    idPatient = Integer.parseInt(window.tablePatient1.getValueAt(window.tablePatient1.getSelectedRow(), 0).toString());
                    //TODO CARGAR DATOS EN LAS CAJAS Y HABILITAR MODIFICAR Y BORRAR
                    Object[] datos = methods.selectPatient(idPatient);
                    window.tfName.setText(String.valueOf(datos[1]));
                    window.tfSurname.setText(String.valueOf(datos[2]));
                    window.jdateChooserP.setDate(Date.valueOf(datos[3].toString()));
                    window.tfAddress.setText(String.valueOf(datos[4]));

                    window.jbModP.setEnabled(true);
                    window.jbDelP.setEnabled(true);
                }
            }
        });

        window.tablePatient2.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                window.tablePatient1.clearSelection();
                window.tableMedic.clearSelection();
                if (window.tablePatient2.isRowSelected(window.tablePatient2.getSelectedRow()))
                {
                    idPatient2 = Integer.parseInt(window.tablePatient2.getValueAt(window.tablePatient2.getSelectedRow(), 0).toString());
                    //TODO CARGAR EPISODIOS Y HABILITAR BOTONES
                }
            }
        });
    }

    public void ColumnNames()
    {
        String[] M = {"Id", "Name", "Surname", "Address", "Med. Centre", "Email", "Medical Speciality", "Telephone", "Birth Date"};
        for(int i=0; i<M.length; i++)
        {
            ColumnMedicTable.addColumn(M[i]);
        }
        String[] P = {"Id", "Name", "Surname", "birthDate", "Address"};
        for(int i=0; i<P.length; i++)
        {
            ColumnPatientTable.addColumn(P[i]);
        }
        String[] E = {"Id", "Description", "Start Date", "End Date", "Ecolution"};
        for(int i=0; i<E.length; i++)
        {
            ColumnEpisodeTable.addColumn(E[i]);
        }
        String[] A = {"Id", "Analysis Date", "Analysis Type", "Report", "Report Date"};
        for(int i=0; i<A.length; i++)
        {
            ColumnAnalysisTable.addColumn(A[i]);
        }
        String[] R = {"Id", "Report Date", "Rpt. Date", "Rad. Date", "Study", "Report", "Ctrl. Done"};
        for(int i=0; i<R.length; i++)
        {
            ColumnRadiographyTable.addColumn(R[i]);
        }
        String[] Ph = {"Id", "Descript.", "Dosage", "Start Date", "End Date", "In. Weight", "Fin. Weight"};
        for(int i=0; i<Ph.length; i++)
        {
            ColumnPharmacotherapyTable.addColumn(Ph[i]);
        }
    }

    public void loadMedic()
    {
        ArrayList<Object[]> list = methods.SelectAllMedic();

        if (list != null)
        {
            ColumnMedicTable.setNumRows(0);
            for (int i = 0;i < list.size(); i++)
            {
                ColumnMedicTable.addRow(list.get(i));
            }
        }
    }

    public void loadPatient(String user, String pass)
    {
        ArrayList<Object[]> list = methods.selectAllPatient(user, pass);

        if (list != null)
        {
            ColumnPatientTable.setNumRows(0);
            for (int i = 1;i < list.size(); i++)
            {
                ColumnPatientTable.addRow(list.get(i));
            }
        }
    }

    public void loadPatient2()
    {
        ArrayList<Object[]> list = methods.selectAllPatient();

        if (list != null)
        {
            ColumnPatientTable.setNumRows(0);
            for (int i = 0;i < list.size(); i++)
            {
                ColumnPatientTable.addRow(list.get(i));
            }
        }
    }

    public void connect()
    {
        window.tabbedPane1.setEnabledAt(0, true);
        window.tabbedPane1.setEnabledAt(1, true);
        window.tabbedPane1.setEnabledAt(2, true);
    }

    public void  disconect()
    {
        window.tabbedPane1.setEnabledAt(0, false);
        window.tabbedPane1.setEnabledAt(1, false);
        window.tabbedPane1.setEnabledAt(2, false);

        window.tabbedPaneHistory.setEnabled(false);

        window.tfName.setEnabled(false);
        window.tfSurname.setEnabled(false);
        window.tfAddress.setEnabled(false);
        window.jdateChooserP.setEnabled(false);

        window.jbModP.setEnabled(false);
        window.jbDelP.setEnabled(false);
        window.jbSaveP.setEnabled(false);
        window.jbCancel.setEnabled(false);

        window.jbAddE.setEnabled(false);
        window.jbModE.setEnabled(false);
        window.jbDelE.setEnabled(false);
    }

    public void newPatient()
    {
        window.tfName.setEnabled(true);
        window.tfSurname.setEnabled(true);
        window.tfAddress.setEnabled(true);
        window.jdateChooserP.setEnabled(true);
        window.jbSaveP.setEnabled(true);

        window.tfName.setText("");
        window.tfSurname.setText("");
        window.tfAddress.setText("");
        window.jdateChooserP.setDate(null);

        window.jbNewP.setEnabled(false);
        window.jbSaveP.setEnabled(true);
        window.jbCancel.setEnabled(true);
    }

    public void endNewPatient()
    {
        window.tfName.setEnabled(false);
        window.tfSurname.setEnabled(false);
        window.tfAddress.setEnabled(false);
        window.jdateChooserP.setEnabled(false);

        window.tfName.setText("");
        window.tfSurname.setText("");
        window.tfAddress.setText("");
        window.jdateChooserP.setDate(null);

        window.jbNewP.setEnabled(true);
        window.jbSaveP.setEnabled(false);
        window.jbCancel.setEnabled(false);
    }

    //TODO CAMBIAS DE PESTA�A, TE DESCONECTAS ETC
    public void dishableMedic()
    {
        idMedic = 0;
        window.jbModM.setEnabled(false);
        window.jbDelM.setEnabled(false);
    }

    public void enableMedic()
    {
        window.jbModM.setEnabled(true);
        window.jbDelM.setEnabled(true);
    }

    /**
     *
     * @param e
     *
     * Este m�todo se encarga de controlar los eventos de boton y menu.
     * Estos eventos van desde a�adir paciente o eliminarlo, hasta Logearse con un medico en cuestion.
     *
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();

        if(source.getClass() == JButton.class)
        {
            String actionCommand = ((JButton) e.getSource()).getActionCommand();
            switch (actionCommand)
            {
                case "New Patient":
                    newPatient();
                    break;
                case "Modify Patient":
                    //TODO UPDATE PATIENT
                    loadPatient2();
                    break;
                case "Cancel Modification":
                    endNewPatient();
                    break;
                case "Save Patient":
                    if(window.tfName.getText().equals("") || window.tfSurname.getText().equals("") || window.tfAddress.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Please, fill each field", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        if(methods.existsPatient(window.tfName.getText(), window.tfSurname.getText())==true)
                        {
                            JOptionPane.showMessageDialog(null, "This patient already exists", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            methods.insertPatient(window.tfName.getText(), window.tfSurname.getText(), new Date(window.jdateChooserP.getDate().getTime()), window.tfAddress.getText());
                            loadPatient2();
                        }
                    }
                    endNewPatient();
                    break;
                case "Delete Patient":
                    //TODO DELETE PATIENT
                    break;
                case "Modify Medic":
                    JTextField fieldUser = new JTextField();
                    JTextField fieldPassword = new JTextField();
                    Object[] check = {"User", fieldUser, "Password", fieldPassword};
                    int op = JOptionPane.showConfirmDialog(null, check, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
                    int sw1 = 0;

                    if(op == JOptionPane.CANCEL_OPTION || op == JOptionPane.CLOSED_OPTION)
                    {
                        sw1 = 1;
                    }
                    else
                    {
                        if (fieldUser.getText().equals("") || fieldPassword.getText().equals(""))
                        {

                            JOptionPane.showMessageDialog(null, "Please, fill each field", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            if(methods.existsMedic2(fieldUser.getText(), fieldPassword.getText(), idMedic) == false)
                            {
                                JOptionPane.showMessageDialog(null, "You can't modify this user", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Successul", "Successful", JOptionPane.INFORMATION_MESSAGE);
                                sw1 = 1;

                                Object[] datos = methods.selectMedic(idMedic);

                                JTextField field2 = new JTextField();
                                field2.setText(datos[1].toString());
                                JTextField field3 = new JTextField();
                                field3.setText(datos[2].toString());
                                JTextField field4 = new JTextField();
                                field4.setText(datos[3].toString());
                                JTextField field5 = new JTextField();
                                field5.setText(datos[4].toString());
                                JTextField field6 = new JTextField();
                                field6.setText(datos[5].toString());
                                JTextField field7 = new JTextField();
                                field7.setText(datos[6].toString());
                                JTextField field8 = new JTextField();
                                field8.setText(datos[7].toString());
                                JDateChooser field9 = new JDateChooser();
                                field9.setDate((Date)datos[8]);


                                int sw = 0;
                                int option = 0;

                                while (sw == 0)
                                {
                                    Object[] message = {"Name", field2, "Surname", field3, "Address", field4,
                                            "Medical Centre", field5, "Email", field6, "Medical Speciality", field7, "Telephone", field8, "BirthDate", field9};
                                    option = JOptionPane.showConfirmDialog(null, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);

                                    if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION)
                                    {
                                        sw = 1;
                                    }
                                    else
                                    {
                                        if (field2.getText().equals("") || field3.getText().equals("") || field4.getText().equals("")
                                                || field5.getText().equals("") || field7.getText().equals("") || field8.getText().equals(""))
                                        {
                                            JOptionPane.showMessageDialog(null, "Please, fill each field", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else
                                        {
                                            JOptionPane.showMessageDialog(null, "Successul", "Successful", JOptionPane.INFORMATION_MESSAGE);
                                            methods.updateMedic(field2.getText(), field3.getText(), field4.getText(), field5.getText(), field6.getText(), field7.getText(), field8.getText(), new Date(field9.getDate().getTime()), idMedic);
                                            sw = 1;
                                            loadMedic();
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                case "Delete Medic":
                    fieldUser = new JTextField();
                    fieldPassword = new JTextField();
                    Object[] check2 = {"User", fieldUser, "Password", fieldPassword};
                    op = JOptionPane.showConfirmDialog(null, check2, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
                    sw1 = 0;

                    if(op == JOptionPane.CANCEL_OPTION || op == JOptionPane.CLOSED_OPTION)
                    {
                        sw1 = 1;
                    }
                    else
                    {
                        if (fieldUser.getText().equals("") || fieldPassword.getText().equals(""))
                        {

                            JOptionPane.showMessageDialog(null, "Please, fill each field", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            if (methods.existsMedic2(fieldUser.getText(), fieldPassword.getText(), idMedic) == false)
                            {
                                JOptionPane.showMessageDialog(null, "You can't modify this user", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Successul", "Successful", JOptionPane.INFORMATION_MESSAGE);
                                methods.deleteMedic(idMedic);
                                loadMedic();

                                if(methods.existsMedic3(idMedic) == false)
                                {
                                    disconect();
                                    window.jlMedic.setText("You need to log in");
                                }
                            }
                        }
                    }
                    break;
                case "ENTER":
                    if(!window.tfUser.getText().equals("") || !window.tfPassword.getText().equals(""))
                    {
                        if(methods.existsMedic(window.tfUser.getText(), window.tfPassword.getText()) == true)
                        {
                            connect();
                            JOptionPane.showMessageDialog(null, "Correct", "Correct", JOptionPane.INFORMATION_MESSAGE);
                            window.jlMedic.setText(window.tfUser.getText());
                            methods.medicConnected = methods.getCollegiateNumber(window.tfUser.getText(), window.tfPassword.getText());
                            loadPatient2();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "There is no medic with that user and password", "Error", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Please, fill each field", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                case "REGISTER":
                    JTextField field1 = new JTextField();
                    JTextField field2 = new JTextField();
                    JTextField field3 = new JTextField();
                    JTextField field4 = new JTextField();
                    JTextField field5 = new JTextField();
                    JTextField field6 = new JTextField();
                    JTextField field7 = new JTextField();
                    JTextField field8 = new JTextField();
                    JTextField field9 = new JTextField();
                    JDateChooser field10 = new JDateChooser();

                    int sw = 0;
                    int option = 0;

                    while (sw == 0)
                    {
                        Object[] message = {"User Name", field1, "User Password", field2, "Name", field3, "Surname", field4,
                                "Address", field5, "Medical Centre", field6, "Email", field7, "Medical Speciality", field8, "Telephone", field9, "Birth Date", field10};
                        option = JOptionPane.showConfirmDialog(null, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);

                        if(option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION)
                        {
                            sw = 1;
                        }
                        else
                        {
                            if (field1.getText().equals("") || field2.getText().equals("") || field3.getText().equals("") || field4.getText().equals("") || field5.getText().equals("") || field6.getText().equals("") || field7.getText().equals("") || field8.getText().equals("") || field9.getText().equals(""))
                            {
                                JOptionPane.showMessageDialog(null, "Please, fill each field", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            else
                            {
                                if(methods.existsMedic(field1.getText(), field2.getText()))
                                {
                                    sw = 0;
                                    JOptionPane.showMessageDialog(null, "This user already exists", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(null, "Successul", "Successful", JOptionPane.INFORMATION_MESSAGE);
                                    sw = 1;
                                    methods.insertMedic(field1.getText(), field2.getText(), field3.getText(), field4.getText(), field5.getText(), field6.getText(), field7.getText(), field8.getText(), field9.getText(), new Date(field10.getDate().getTime()));
                                    loadMedic();
                                }
                            }
                        }
                    }
                    break;
                case "See Episodes":
                    break;
                case "Add Episodes":
                    break;
                case "Modify Episodes":
                    break;
                case "Delete Episodes":

                    break;
                case "See Analysis":

                    break;
                case "Modify Analysis":

                    break;
                case "Delete Analysis":

                    break;
                case "Add Radiography":

                    break;
                case "See Radiography":

                    break;
                case "Modify Radiography":

                    break;
                case "Delete Radiography":

                    break;
                case "Add Pharmacotherapy":

                    break;
                case "See Pharmacotherapy":

                    break;
                case "Modify Pharmacotherapy":

                    break;
                case "Delete Pharmacotherapy":

                    break;
                default:
                    break;
            }
        }
        else
        {
            String actionCommand2 = ((JMenuItem) e.getSource()).getActionCommand();

            switch (actionCommand2)
            {
                case "Save":

                    break;
                case "Save as":

                    break;
                case "Import":

                    break;
                case "Export":

                    break;
                case "Change File Path":

                    break;
                case "Exit":
                    System.exit(0);
                    break;
                case "Connect":
                    c = new JConnection(this, this.methods);
                    c.mostrar();
                    break;
                case "Disconnect":
                    if(c != null)
                    {
                        c.desconectar();
                        disconect();
                    }
                    break;
                default:
                    break;
            }
        }
    }
}