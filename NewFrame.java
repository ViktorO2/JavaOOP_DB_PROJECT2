import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewFrame extends JFrame {


    Connection conn = null;
    PreparedStatement state = null;
    ResultSet result = null;
    int id = -1;


    //person-------------------
    JPanel panelPerson = new JPanel();
    JPanel upPanelPerson = new JPanel();
    JPanel midPanelPerson = new JPanel();
    JPanel downPanelPerson = new JPanel();

    JLabel fnameL = new JLabel("Име:");
    JLabel lnameL = new JLabel("Фамилия:");
    JLabel sexL = new JLabel("Пол:");
    JLabel ageL = new JLabel("Години:");
    JLabel salaryL = new JLabel("Заплата:");

    JTextField fnameTF = new JTextField();
    JTextField lnameTF = new JTextField();
    JTextField ageTF = new JTextField();
    JTextField salaryTF = new JTextField();

    String[] itemSex = {"Мъж", "Жена"};

    JComboBox<String> sexCombo = new JComboBox<String>(itemSex);
    JComboBox<String> personCombo = new JComboBox<String>();

    JTable tablePerson = new JTable();
    JScrollPane myScrollPerson = new JScrollPane(tablePerson);

    JButton addBtPerson = new JButton("Добавяне");
    JButton deleteBtPerson = new JButton("Изтриване");
    JButton editBtPerson = new JButton("Редактиране");
    JButton searchBtPerson = new JButton("Търсене по заплата");
    JButton refreshBtPerson = new JButton("Обнови");

    //car------------------------------------
    JPanel panelCar = new JPanel();
    JPanel upPanelCar = new JPanel();
    JPanel midPanelCar = new JPanel();
    JPanel downPanelCar = new JPanel();

    JLabel carBrandL = new JLabel("Марка кола:");
    JLabel carModelL = new JLabel("Модел:");
    JLabel carYearL = new JLabel("Година на производсто:");
    JLabel carPowerL = new JLabel("Мощност:");
    JLabel carTypeL = new JLabel("Категория:");
    JLabel carRentPriceL = new JLabel("Цена за наемане:");

    JTextField carBrandTF = new JTextField();
    JTextField carModelTF = new JTextField();
    JTextField carYearTF = new JTextField();
    JTextField carPowerTF = new JTextField();
    JTextField carTypeTF = new JTextField();
    JTextField carRentPriceTF = new JTextField();

    JTable tableCar = new JTable();
    JScrollPane myScrollCar = new JScrollPane(tableCar);

    JButton addBtCar = new JButton("Добавяне");
    JButton deleteBtCar = new JButton("Изтриване");
    JButton editBtCar = new JButton("Редактиране");
    JButton searchBtCar = new JButton("Търсене по бранд");
    JButton refreshBtCar = new JButton("Обнови");


    // Rent---------------------------------------
    JPanel panelRenta = new JPanel();
    JPanel panelSpr = new JPanel();
    JTabbedPane tab = new JTabbedPane();


    public NewFrame() {
        this.setSize(400, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        tab.add(panelPerson, "Клиенти");
        tab.add(panelCar, "Коли");
        tab.add(panelRenta, "Наем");
        tab.add(panelSpr, "Справка по ...");

        this.add(tab);

        this.setVisible(true);

        //PANEL PERSON -------------------------------
        panelPerson.setLayout(new GridLayout(3, 1));


        //upPanelPerson-------------------------------
        upPanelPerson.setLayout(new GridLayout(5, 2));
        upPanelPerson.add(fnameL);
        upPanelPerson.add(fnameTF);
        upPanelPerson.add(lnameL);
        upPanelPerson.add(lnameTF);
        upPanelPerson.add(sexL);
        upPanelPerson.add(sexCombo);
        upPanelPerson.add(ageL);
        upPanelPerson.add(ageTF);
        upPanelPerson.add(salaryL);
        upPanelPerson.add(salaryTF);

        panelPerson.add(upPanelPerson);

        //midPanelPerson-------------------------------
        midPanelPerson.add(addBtPerson);
        midPanelPerson.add(deleteBtPerson);
        midPanelPerson.add(editBtPerson);
        midPanelPerson.add(searchBtPerson);
        midPanelPerson.add(refreshBtPerson);
        midPanelPerson.add(personCombo);

        addBtPerson.addActionListener(new AddActionPerson());
        deleteBtPerson.addActionListener(new DeleteActionPerson());
        searchBtPerson.addActionListener(new SearchActionPerson());
        refreshBtPerson.addActionListener(new RefreshActionPerson());
        editBtPerson.addActionListener(new EditActionPerson());
        panelPerson.add(midPanelPerson);

        //downPanelPerson------------------------------

        myScrollPerson.setPreferredSize(new Dimension(350, 150));
        downPanelPerson.add(myScrollPerson);
        panelPerson.add(downPanelPerson);
        refreshTablePerson();
        tablePerson.addMouseListener(new MouseActionPerson());
        refreshComboPerson();

        this.setVisible(true);

        //PANEL CAR -------------------------------------
        panelCar.setLayout(new GridLayout(3, 1));

        //upPanelCar----------------------------------
        upPanelCar.setLayout(new GridLayout(6, 2));
        upPanelCar.add(carBrandL);
        upPanelCar.add(carBrandTF);
        upPanelCar.add(carModelL);
        upPanelCar.add(carModelTF);
        upPanelCar.add(carYearL);
        upPanelCar.add(carYearTF);
        upPanelCar.add(carPowerL);
        upPanelCar.add(carPowerTF);
        upPanelCar.add(carTypeL);
        upPanelCar.add(carTypeTF);
        upPanelCar.add(carRentPriceL);
        upPanelCar.add(carRentPriceTF);

        panelCar.add(upPanelCar);

        //midPanelPerson-------------------------------
        midPanelCar.add(addBtCar);
        midPanelCar.add(deleteBtCar);
        midPanelCar.add(editBtCar);
        midPanelCar.add(searchBtCar);
        midPanelCar.add(refreshBtCar);

        addBtCar.addActionListener(new AddActionCar());
        deleteBtCar.addActionListener(new DeleteActionCar());
        searchBtCar.addActionListener(new SearchActionCar());
        refreshBtCar.addActionListener(new RefreshActionCar());
        editBtCar.addActionListener(new EditActionCar());

        panelCar.add(midPanelCar);

        //downPanelCar------------------------------
        myScrollCar.setPreferredSize(new Dimension(350, 150));
        downPanelCar.add(myScrollCar);
        panelCar.add(downPanelCar);
        refreshTableCar();
        tableCar.addMouseListener(new MouseActionCar());


        this.setVisible(true);

    }

// Person Method----------------------------------
    public void refreshTablePerson() {
        conn = DBConnection.getConnection();

        try {
            state = conn.prepareStatement("select * from person");
            result = state.executeQuery();
            tablePerson.setModel(new MyModel(result));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void clearFormPerson() {
        fnameTF.setText("");
        lnameTF.setText("");
        ageTF.setText("");
        salaryTF.setText("");
    }

    class AddActionPerson implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "insert into person(fname,lname,sex,age,salary) values(?,?,?,?,?)";
            try {
                state = conn.prepareStatement(sql);
                state.setString(1, fnameTF.getText());
                state.setString(2, lnameTF.getText());
                state.setString(3, sexCombo.getSelectedItem().toString());
                state.setInt(4, Integer.parseInt(ageTF.getText()));
                state.setFloat(5, Float.parseFloat(salaryTF.getText()));
                state.execute();
                refreshTablePerson();
                refreshComboPerson();
                clearFormPerson();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    class EditActionPerson implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            if (id > 0) {
                String sql = "update person set fname=?, lname=?, sex=?, age=?, salary=? where id=? ";

                try {
                    state = conn.prepareStatement(sql);

                    state.setString(1, fnameTF.getText());
                    state.setString(2, lnameTF.getText());
                    state.setString(3, sexCombo.getSelectedItem().toString());
                    state.setInt(4, Integer.parseInt(ageTF.getText()));
                    state.setFloat(5, Float.parseFloat(salaryTF.getText()));
                    state.setInt(6, id);
                    state.execute();
                    refreshTablePerson();
                    refreshComboPerson();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    class MouseActionPerson implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = tablePerson.getSelectedRow();
            id = Integer.parseInt(tablePerson.getValueAt(row, 0).toString());
            fnameTF.setText(tablePerson.getValueAt(row, 1).toString());
            lnameTF.setText(tablePerson.getValueAt(row, 2).toString());
            ageTF.setText(tablePerson.getValueAt(row, 4).toString());
            salaryTF.setText(tablePerson.getValueAt(row, 5).toString());
            if (tablePerson.getValueAt(row, 3).toString().equals("Мъж")) {
                sexCombo.setSelectedIndex(0);
            } else {
                sexCombo.setSelectedIndex(1);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

    }

    class DeleteActionPerson implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "delete from person where id=? ";
            try {
                state = conn.prepareStatement(sql);
                state.setInt(1, id);
                state.execute();
                refreshTablePerson();
                refreshComboPerson();
                clearFormPerson();
                id = -1;

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    class SearchActionPerson implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "select * from person where salary=?";

            try {
                state = conn.prepareStatement(sql);
                state.setFloat(1, Float.parseFloat(salaryTF.getText()));
                result = state.executeQuery();
                tablePerson.setModel(new MyModel(result));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    class RefreshActionPerson implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clearFormPerson();
            refreshTablePerson();

        }
    }

    public void refreshComboPerson() {
        personCombo.removeAllItems();
        String sql = "select id,fname,lname from person";
        conn = DBConnection.getConnection();
        String item = "";

        try {
            state = conn.prepareStatement(sql);
            result = state.executeQuery();
            while (result.next()) {
                item = result.getObject(1).toString() + "." +
                        result.getObject(2).toString() + " " +
                        result.getObject(3).toString();
                personCombo.addItem(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//CAR METHOD------------------------------------------------

    public void refreshTableCar() {
        conn = DBConnection.getConnection();

        try {
            state = conn.prepareStatement("select * from car");
            result = state.executeQuery();
            tableCar.setModel(new MyModel(result));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void clearFormCar(){
        carBrandTF.setText("");
        carModelTF.setText("");
        carYearTF.setText("");
        carPowerTF.setText("");
        carTypeTF.setText("");
        carRentPriceTF.setText("");
    }

    class AddActionCar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "insert into car(brand,model,caryear,power,type,price) values(?,?,?,?,?,?)";
            try {
                state = conn.prepareStatement(sql);
                state.setString(1, carBrandTF.getText());
                state.setString(2, carModelTF.getText());
                state.setInt(3, Integer.parseInt(carYearTF.getText()));
                state.setInt(4, Integer.parseInt(carPowerTF.getText()));
                state.setString(5, carTypeTF.getText());
                state.setFloat(6,Float.parseFloat(carRentPriceTF.getText()));

                state.execute();
                refreshTableCar();
                clearFormCar();

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    class EditActionCar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
         conn=DBConnection.getConnection();
         if(id>0){
             String sql="update car set brand=?, model=?,caryear=?,power=?,type=?,price=? where id=?";

             try{
                 state = conn.prepareStatement(sql);
                 state.setString(1, carBrandTF.getText());
                 state.setString(2, carModelTF.getText());
                 state.setInt(3, Integer.parseInt(carYearTF.getText()));
                 state.setInt(4, Integer.parseInt(carPowerTF.getText()));
                 state.setString(5, carTypeTF.getText());
                 state.setFloat(6,Float.parseFloat(carRentPriceTF.getText()));
                 state.setInt(7,id);

                 state.execute();
                 refreshTableCar();

             } catch (SQLException e1) {
                 e1.printStackTrace();;
             }

         }
        }
    }

    class MouseActionCar implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            int row= tableCar.getSelectedRow();
            id=Integer.parseInt(tableCar.getValueAt(row,0).toString());
            carBrandTF.setText(tableCar.getValueAt(row,1).toString());
            carModelTF.setText(tableCar.getValueAt(row,2).toString());
            carYearTF.setText(tableCar.getValueAt(row,3).toString());
            carPowerTF.setText(tableCar.getValueAt(row,4).toString());
            carTypeTF.setText(tableCar.getValueAt(row,5).toString());
            carRentPriceTF.setText(tableCar.getValueAt(row,6).toString());

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    class DeleteActionCar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            conn=DBConnection.getConnection();
            String sql="delete from car where id=?";
            try {
            state=conn.prepareStatement(sql);
            state.setInt(1,id);
            state.execute();
            refreshTableCar();
            clearFormCar();
            id = -1;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }
    }

    class SearchActionCar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            conn=DBConnection.getConnection();
            String sql="select * from car where brand=?";

            try {
            state=conn.prepareStatement(sql);
            state.setString(1,carBrandTF.getText());
            result=state.executeQuery();
            tableCar.setModel(new MyModel(result));

            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    class RefreshActionCar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            refreshTableCar();
            clearFormCar();
        }
    }

}