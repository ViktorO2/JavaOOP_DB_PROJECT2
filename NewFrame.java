
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;


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
    JComboBox<String> carCombo = new JComboBox<String>();
    JTable tableCar = new JTable();
    JScrollPane myScrollCar = new JScrollPane(tableCar);

    JButton addBtCar = new JButton("Добавяне");
    JButton deleteBtCar = new JButton("Изтриване");
    JButton editBtCar = new JButton("Редактиране");
    JButton searchBtCar = new JButton("Търсене по бранд");
    JButton refreshBtCar = new JButton("Обнови");


    // Rent---------------------------------------
    JPanel panelRent = new JPanel();
    JPanel upPanelRent = new JPanel();
    JPanel midPanelRent = new JPanel();
    JPanel downPanelRent = new JPanel();

    JLabel clientL = new JLabel("Клиент:");
    JLabel carL = new JLabel("Кола:");
    JLabel rentalDateL = new JLabel("Дата на наемане:");
    JLabel returnDateL = new JLabel("Дата на връщане:");


    JTextField returnDateTF = new JTextField();
    JTextField rentalDateTF = new JTextField();

    JComboBox<String> personComboRent = new JComboBox<String>();
    JComboBox<String> carComboRent = new JComboBox<String>();
    JTable rentCarTable = new JTable();
    JScrollPane myScrollRent = new JScrollPane(rentCarTable);


    JButton rentBt = new JButton("Наемане");
    JButton editSpravkaBt = new JButton("Редактиране");
    JButton deleteRentBt = new JButton("Изтриване");
    JButton refreshBtRent = new JButton("Обнови");


    // Spravka---------------------------------------
    JPanel panelSpr = new JPanel();
    JPanel upPanelSpravka = new JPanel();
    JPanel midPanelSpravka = new JPanel();
    JPanel downPanelSpravka = new JPanel();

    JLabel fnameSprL = new JLabel("Справка за клиент според лично име:");
    JLabel priceCarSprL = new JLabel("Цена за наем на автомобил");

    JTextField fnameSprTF = new JTextField();
    JTextField priceCarSprTF = new JTextField();

    JButton firstSprBt = new JButton("Търсене");
    JButton refreshBtSpravka = new JButton("Обнови");
    JTable tableSpravka = new JTable();
    JScrollPane myScrollSpravka = new JScrollPane(tableSpravka);

    JTabbedPane tab = new JTabbedPane();


    public NewFrame() {
        this.setSize(450, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        tab.add(panelPerson, "Клиенти");
        tab.add(panelCar, "Коли");
        tab.add(panelRent, "Наем");
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

        //midPanelCar-------------------------------
        midPanelCar.add(addBtCar);
        midPanelCar.add(deleteBtCar);
        midPanelCar.add(editBtCar);
        midPanelCar.add(searchBtCar);
        midPanelCar.add(refreshBtCar);
        midPanelCar.add(carCombo);

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
        refreshComboCar();

        this.setVisible(true);

        //PANEL RENT -------------------------------
        panelRent.setLayout(new GridLayout(3, 1));

        //upPanelRent----------------------------------
        upPanelRent.setLayout(new GridLayout(4, 2));
        upPanelRent.add(clientL);
        upPanelRent.add(personComboRent);
        upPanelRent.add(carL);
        upPanelRent.add(carComboRent);

        rentalDateTF.setToolTipText("yyyy-MM-dd");
        returnDateTF.setToolTipText("yyyy-MM-dd");
        upPanelRent.add(rentalDateL);
        upPanelRent.add(rentalDateTF);
        upPanelRent.add(returnDateL);
        upPanelRent.add(returnDateTF);


        panelRent.add(upPanelRent);

        //midPanelRent-------------------------------
        midPanelRent.add(rentBt);
        midPanelRent.add(editSpravkaBt);
        midPanelRent.add(deleteRentBt);
        midPanelRent.add(refreshBtRent);

        rentBt.addActionListener(new RentAction());
        editSpravkaBt.addActionListener(new EditActionRent());
        deleteRentBt.addActionListener(new DeleteActionRent());
        refreshBtRent.addActionListener(new RefreshRentAction());

        panelRent.add(midPanelRent);

        //downPanelRent------------------------------
        myScrollRent.setPreferredSize(new Dimension(350, 150));
        downPanelRent.add(myScrollRent);
        panelRent.add(downPanelRent);

        rentCarTable.addMouseListener(new MouseActionRent());
        refreshComboRentPerson();
        refreshComboRentCar();
        refreshTableRent();


        this.setVisible(true);

        //SPRAVKA-------------------------------
        panelSpr.setLayout(new GridLayout(3, 1));

        //upPanelSPRAVKA----------------------------------
        upPanelSpravka.setLayout(new GridLayout(4, 2));
        upPanelSpravka.add(fnameSprL);
        upPanelSpravka.add(fnameSprTF);
        upPanelSpravka.add(priceCarSprL);
        upPanelSpravka.add(priceCarSprTF);

        panelSpr.add(upPanelSpravka);

        //midPanelSPRAVKA-------------------------------
        midPanelSpravka.add(firstSprBt);
        midPanelSpravka.add(refreshBtSpravka);

        firstSprBt.addActionListener(new SearchSpravkaAction());
        refreshBtSpravka.addActionListener(new RefreshActionSpravka());

        panelSpr.add(midPanelSpravka);

        //downPanelSPRAVKA------------------------------
        myScrollSpravka.setPreferredSize(new Dimension(350, 150));
        downPanelSpravka.add(myScrollSpravka);
        panelSpr.add(downPanelSpravka);


        refreshTableSpravka();


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

    public void refreshComboPerson() {
        personCombo.removeAllItems();
        String sql = "select id,fname,lname from person";
        conn = DBConnection.getConnection();
        String item = "";

        try {
            state = conn.prepareStatement(sql);
            result = state.executeQuery();
            while (result.next()) {
                item = result.getObject(1).toString() + " . " +
                        result.getObject(2).toString() + " " +
                        result.getObject(3).toString();
                personCombo.addItem(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public void clearFormCar() {
        carBrandTF.setText("");
        carModelTF.setText("");
        carYearTF.setText("");
        carPowerTF.setText("");
        carTypeTF.setText("");
        carRentPriceTF.setText("");
    }

    public void refreshComboCar() {

        carCombo.removeAllItems();
        String sql = "select id,brand,model, power from car";
        conn = DBConnection.getConnection();
        String item = "";

        try {
            state = conn.prepareStatement(sql);
            result = state.executeQuery();
            while (result.next()) {
                item = result.getObject(1).toString() + ". " +
                        result.getObject(2).toString() + " - " +
                        result.getObject(3).toString() + " - " +
                        result.getObject(4).toString() + "к.с.";
                carCombo.addItem(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                state.setFloat(6, Float.parseFloat(carRentPriceTF.getText()));

                state.execute();
                refreshTableCar();
                refreshComboCar();
                clearFormCar();

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    class EditActionCar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            if (id > 0) {
                String sql = "update car set brand=?, model=?,caryear=?,power=?,type=?,price=? where id=?";

                try {
                    state = conn.prepareStatement(sql);
                    state.setString(1, carBrandTF.getText());
                    state.setString(2, carModelTF.getText());
                    state.setInt(3, Integer.parseInt(carYearTF.getText()));
                    state.setInt(4, Integer.parseInt(carPowerTF.getText()));
                    state.setString(5, carTypeTF.getText());
                    state.setFloat(6, Float.parseFloat(carRentPriceTF.getText()));
                    state.setInt(7, id);

                    state.execute();
                    refreshTableCar();

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        }
    }

    class MouseActionCar implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int row = tableCar.getSelectedRow();
            id = Integer.parseInt(tableCar.getValueAt(row, 0).toString());
            carBrandTF.setText(tableCar.getValueAt(row, 1).toString());
            carModelTF.setText(tableCar.getValueAt(row, 2).toString());
            carYearTF.setText(tableCar.getValueAt(row, 3).toString());
            carPowerTF.setText(tableCar.getValueAt(row, 4).toString());
            carTypeTF.setText(tableCar.getValueAt(row, 5).toString());
            carRentPriceTF.setText(tableCar.getValueAt(row, 6).toString());

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

    class DeleteActionCar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "delete from car where id=?";
            try {
                state = conn.prepareStatement(sql);
                state.setInt(1, id);
                state.execute();
                refreshTableCar();
                refreshComboCar();
                clearFormCar();
                id = -1;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }
    }

    class SearchActionCar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "select * from car where brand=?";

            try {
                state = conn.prepareStatement(sql);
                state.setString(1, carBrandTF.getText());
                result = state.executeQuery();
                tableCar.setModel(new MyModel(result));

            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    class RefreshActionCar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            refreshTableCar();
            clearFormCar();
        }
    }


    //RENT METHOD------------------------------------------------
    public void refreshComboRentPerson() {
        personComboRent.removeAllItems();
        String sql = "select id, fname,lname from person";
        conn = DBConnection.getConnection();
        String item = "";

        try {
            state = conn.prepareStatement(sql);
            result = state.executeQuery();
            while (result.next()) {
                item = result.getObject(1).toString() + "." +
                        result.getObject(2).toString() + " " +
                        result.getObject(3).toString();
                personComboRent.addItem(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void refreshComboRentCar() {

        carComboRent.removeAllItems();
        String sql = "select id,brand,model, power from car";
        conn = DBConnection.getConnection();
        String item = "";

        try {
            state = conn.prepareStatement(sql);
            result = state.executeQuery();
            while (result.next()) {
                item = result.getObject(1).toString() + "." +
                        result.getObject(2).toString() + " - " +
                        result.getObject(3).toString() + " - " +
                        result.getObject(4).toString() + "к.с.";
                carComboRent.addItem(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void refreshTableRent() {
        conn = DBConnection.getConnection();

        try {
            state = conn.prepareStatement("SELECT R.id,fname, lname, brand, rental_date, return_date\n" +
                    "FROM rentcar R \n" +
                    "JOIN person P ON R.person_id = P.id \n" +
                    "JOIN car C ON R.car_id = C.id;");
            result = state.executeQuery();
            rentCarTable.setModel(new MyModel(result));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void clearFormRent() {
        rentalDateTF.setText("");
        returnDateTF.setText("");
    }

    class RentAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String comboSTRPerson = personComboRent.getSelectedItem().toString();
            String comboSTRCar = carComboRent.getSelectedItem().toString();


            conn = DBConnection.getConnection();
            String sql = "insert into RENTCAR(person_id,car_id,rental_date,return_date) values(?,?,?,?)";
            try {
                state = conn.prepareStatement(sql);
                state.setInt(1, Integer.parseInt(comboSTRPerson.substring(0, comboSTRPerson.indexOf('.')).toString()));
                state.setInt(2, Integer.parseInt(comboSTRCar.substring(0, comboSTRCar.indexOf('.')).toString()));
                state.setDate(3, Date.valueOf(rentalDateTF.getText()));
                state.setDate(4, Date.valueOf(returnDateTF.getText()));

                state.execute();
                refreshComboPerson();
                refreshComboCar();
                refreshTableRent();
                clearFormRent();


            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }
    }

    class MouseActionRent implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            int row = rentCarTable.getSelectedRow();
            id = Integer.parseInt(rentCarTable.getValueAt(row, 0).toString());
            rentalDateTF.setText(rentCarTable.getValueAt(row, 4).toString());
            returnDateTF.setText(rentCarTable.getValueAt(row, 5).toString());
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

    class DeleteActionRent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "delete from rentcar where id=?";
            try {
                state = conn.prepareStatement(sql);
                state.setInt(1, id);

                state.execute();
                refreshTableRent();
                id = -1;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }
    }

    class EditActionRent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


            conn = DBConnection.getConnection();
            String sql = "update RENTCAR set rental_date=?,return_date=? where id=?";
            try {
                state = conn.prepareStatement(sql);
                state.setDate(1, Date.valueOf(rentalDateTF.getText()));
                state.setDate(2, Date.valueOf(returnDateTF.getText()));
                state.setInt(3, id);


                state.execute();
                refreshTableRent();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class RefreshRentAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            refreshComboRentCar();
            refreshComboRentPerson();
            refreshTableRent();
            clearFormRent();
        }
    }

    //SPRAVKA METHOD---------------------------------------
    public void clearFormSpravka() {
        fnameSprTF.setText("");
        priceCarSprTF.setText("");
    }

    public void refreshTableSpravka() {
        conn = DBConnection.getConnection();
        try {
            state = conn.prepareStatement("Select FNAME, LNAME,AGE,SALARY, BRAND, MODEL, POWER, PRICE\n" +
                    "FROM Rentcar R \n" +
                    "JOIN Person P \n" +
                    "on R.person_id=P.ID\n" +
                    "Join car C\n" +
                    "On R.car_id=C.ID");
            result = state.executeQuery();
            tableSpravka.setModel(new MyModel(result));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    class RefreshActionSpravka implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clearFormSpravka();
            refreshTableSpravka();
        }
    }

    class SearchSpravkaAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();

            String sql = "SELECT FNAME, LNAME, SALARY, BRAND, MODEL, POWER, PRICE\n" +
                    "FROM RENTCAR R\n" +
                    "JOIN PERSON P ON R.PERSON_ID = P.ID\n" +
                    "JOIN CAR C ON R.CAR_ID = C.ID\n" +
                    "WHERE P.FNAME = ? AND C.PRICE <= ?";
            try {
                state = conn.prepareStatement(sql);
                state.setString(1,fnameSprTF.getText());
                state.setDouble( 2 ,Double.parseDouble(priceCarSprTF.getText()));
                result = state.executeQuery();
                tableSpravka.setModel(new MyModel(result));
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        }
    }


}