package presentation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import domain.*;
import domain.model.HoaDonTheoGio;
import domain.model.HoaDonTheoNgay;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class View extends JFrame{
    private HoaDonService hoaDonService;

    private DefaultTableModel tableModel;
    private JTable table;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton findButton;
    private JButton countButton;
    private JButton avgButton;
    private JButton loadButton;

    private JTextField idTextField;
    private JTextField dateTextField;
    private JTextField nameTextField;
    private JTextField idroomTextField;
    private JTextField unitpriceTextField;
    private JTextField dayTextField;
    private JTextField hourTextField;

    //private CommandProcessor commandProcessor;
    public View() {
        //commandProcessor = new CommandProcessor();
        this.hoaDonService = new HoaDonServiceIple();
        // Set up JFrame
        setTitle("Bill management");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create JTable to display student list
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Date");
        tableModel.addColumn("Name Customer");
        tableModel.addColumn("ID Room");
        tableModel.addColumn("Unit Price");
        tableModel.addColumn("Day");
        tableModel.addColumn("Hour");
        tableModel.addColumn("Price");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Tạo JPanel có thông tin khách hàng và các tác vụ
        JPanel inputPanel = new JPanel(new GridLayout(8, 2));
        idTextField = new JTextField();
        dateTextField = new JTextField();
        nameTextField = new JTextField();
        idroomTextField = new JTextField();
        unitpriceTextField = new JTextField();
        hourTextField = new JTextField();
        dayTextField = new JTextField();

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        findButton = new JButton("Find");
        countButton = new JButton("Count ID Room");
        avgButton = new JButton("Average");
        loadButton = new JButton("Load");

        inputPanel.add(new JLabel("ID: "));
        inputPanel.add(idTextField);
        inputPanel.add(new JLabel("Date: "));
        inputPanel.add(dateTextField);
        inputPanel.add(new JLabel("Name Customer: "));
        inputPanel.add(nameTextField);
        inputPanel.add(new JLabel("ID Room(D for Day, H for Hour): "));
        inputPanel.add(idroomTextField);
        inputPanel.add(new JLabel("Unit Price: "));
        inputPanel.add(unitpriceTextField);
        inputPanel.add(new JLabel("Day: "));
        inputPanel.add(dayTextField);
        inputPanel.add(new JLabel("Hour: "));
        inputPanel.add(hourTextField);

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(findButton);
        buttonPanel.add(countButton);
        buttonPanel.add(avgButton);
        buttonPanel.add(loadButton);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners for buttons
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addHoaDon();
            }
        });
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editHoaDon();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteHoaDon();
            }
        });
        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                findHoaDon();
            }
        });
        countButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                countIdRoom();
            }
        });
        avgButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                avgHoaDon();
            }
        });
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // loadAllHoaDonList();
                loadHoaDon();
                
            }
        });
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            // Handle row selection change event
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Get data from the selected row
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    String date = (String) tableModel.getValueAt(selectedRow, 1);
                    String name = (String) tableModel.getValueAt(selectedRow, 2);
                    String idroom = (String) tableModel.getValueAt(selectedRow, 3);
                    int unitprice = (int) tableModel.getValueAt(selectedRow, 4);
                    int day = (int) tableModel.getValueAt(selectedRow, 5);
                    int hour = (int) tableModel.getValueAt(selectedRow, 6);

                    // Set data to the text fields
                    idTextField.setText(String.valueOf(id));
                    dateTextField.setText(date);
                    nameTextField.setText(name);
                    idroomTextField.setText(idroom);
                    unitpriceTextField.setText(String.valueOf(unitprice));
                    dayTextField.setText(String.valueOf(day));
                    hourTextField.setText(String.valueOf(hour));
                }
            }
        }
    });

        // Load initial student list
        loadAllHoaDonList();
    }

    // Method to add a student
    private void addHoaDon() {
        String idText = idTextField.getText();
        String date = dateTextField.getText();
        String nameCustomer = nameTextField.getText();
        String idroom = idroomTextField.getText();
        String unitpriceText = unitpriceTextField.getText();
        String hourText = hourTextField.getText();
        String dayText = dayTextField.getText();

        // Kiểm tra xem các trường dữ liệu có được nhập hay không
        if (idText.isEmpty() || date.isEmpty() || nameCustomer.isEmpty() || idroom.isEmpty() ||
            unitpriceText.isEmpty() || hourText.isEmpty() || dayText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            return;
        }

        int id = Integer.parseInt(idText);
        int unitprice = Integer.parseInt(unitpriceText);
        int hour = Integer.parseInt(hourText);
        int day = Integer.parseInt(dayText);

        // Xác định loại hóa đơn (theo giờ hoặc theo ngày)
        boolean isHourly = hour > 0 && day == 0;
        boolean isDaily = hour == 0 && day > 0;

        // Kiểm tra loại hóa đơn hợp lệ
        if (!isHourly && !isDaily) {
            JOptionPane.showMessageDialog(null, "Please select day or hour.");
            return;
        }

        // Calculate the price based on the selected type (hourly or daily)
        double price = 0.0;
        if (isHourly) {
            if (hour > 24 && hour < 30) {
                price = 24 * unitprice;
            } else {
                price = unitprice * hour;
            }
        } else if (isDaily) {
            if (day > 7) {
                price = 7 * unitprice + (day - 7) * unitprice * 0.8;
            } else {
                price = day * unitprice;
            }
        }

        HoaDonTheoNgay hoaDonTheoNgay = new HoaDonTheoNgay(id, date, nameCustomer, idroom, unitprice, day, hour, price);
        HoaDonTheoGio hoaDonTheoGio = new HoaDonTheoGio(id, date, nameCustomer, idroom, unitprice, day, hour, price);
        hoaDonService.addHoaDon(hoaDonTheoNgay, hoaDonTheoGio);
        //JOptionPane.showMessageDialog(this, "New Invoice has been added!");

        clearFields();
        loadAllHoaDonList();
    }

    // Method to edit a student
    private void editHoaDon() {
        // int row = table.getSelectedRow();
        // if (row == -1) {
        //     JOptionPane.showMessageDialog(this, "Please select a bill to edit.");
        //     return;
        // }

        try {
            int id = Integer.parseInt(idTextField.getText());
            String date = dateTextField.getText();
            String name = nameTextField.getText();
            String idroom = idroomTextField.getText();
            int unitprice = Integer.parseInt(unitpriceTextField.getText());
            int day = Integer.parseInt(dayTextField.getText());
            int hour = Integer.parseInt(hourTextField.getText());

            // Validate input data
            if (unitprice <= 0 || day < 0 || hour < 0) {
                JOptionPane.showMessageDialog(this, "Invalid input data. Please check your inputs.");
                return;
            }

            // Calculate the price based on the input
            double price = 0.0;

            HoaDonTheoGio hoaDonTheoGio = new HoaDonTheoGio(id, date, name, idroom, unitprice, day, hour, price);
            HoaDonTheoNgay hoaDonTheoNgay = new HoaDonTheoNgay(id, date, name, idroom, unitprice, day, hour, price);

            //JOptionPane.showMessageDialog(this, "Bill has been edited.");

            hoaDonService.editHoaDon(hoaDonTheoNgay, hoaDonTheoGio);

            clearFields();
            loadAllHoaDonList();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input data. Please check your inputs.");
        }
    }

    // Method to delete a student
    private void deleteHoaDon() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a bill to delete.");
            return;
        }

        int id = (int) tableModel.getValueAt(row, 0);
        JOptionPane.showMessageDialog(this, "Do you want to delete it?");
        hoaDonService.deleteHoaDon(id);

        clearFields();
        loadAllHoaDonList();
    }

    // Method to find a student
    private void findHoaDon() {
        String idInput = JOptionPane.showInputDialog(this, "Enter Bill ID:");
        if (idInput == null || idInput.isEmpty()) {
            return; // Không thực hiện tìm kiếm nếu không có ID
        }

        int id = Integer.parseInt(idInput);
        HoaDonTheoGio hoaDonTheoGio = hoaDonService.getGioById(id);
        HoaDonTheoNgay hoaDonTheoNgay = hoaDonService.getNgayById(id);
        tableModel.setRowCount(0);

        if (hoaDonTheoGio != null || hoaDonTheoNgay != null) {
            double thanhTien = 0.0;

            if (hoaDonTheoGio != null) {
                thanhTien += hoaDonTheoGio.thanhTien();
            }

            if (hoaDonTheoNgay != null) {
                thanhTien += hoaDonTheoNgay.thanhTien();
            }

            Object[] rowData = {
                hoaDonTheoGio.getId(),
                hoaDonTheoGio.getDate(),
                hoaDonTheoGio.getNameCustomer(),
                hoaDonTheoGio.getRoomId(),
                hoaDonTheoGio.getUnitPrice(),
                hoaDonTheoNgay.getDay(),
                hoaDonTheoGio.getHour(),
                thanhTien
            };
            tableModel.addRow(rowData);
        } else {
            JOptionPane.showMessageDialog(this, "Bill not found.");
            clearFields();
        }
    }
    
    //Method to count id room
    private void countIdRoom() {

        JDialog dialog = new JDialog(this, "Count Invoice");
        dialog.setLayout(new GridLayout(4, 2, 10, 10));

        // Tạo một ComboBox với các lựa chọn "DemNgay" và "DemGio"
        String[] options = {"Days", "Hours"};
        JComboBox<String> comboBox = new JComboBox<>(options);

        JButton demButton = new JButton("Count Invoices");

        JTextField resultTextField = new JTextField();
        resultTextField.setEditable(false);

        // Xử lý sự kiện khi nhấn nút "Đếm Hóa Đơn"
        demButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
                String idRoomType = selectedOption.equals("Days") ? "D" : "H";
                int count = hoaDonService.countIdRoom(idRoomType);
                resultTextField.setText(String.valueOf(count));
            }
        });

        dialog.add(new JLabel("Select type:"));
        dialog.add(comboBox);
        dialog.add(new JLabel()); // Empty label for spacing
        dialog.add(demButton);
        dialog.add(new JLabel("Amount:"));
        dialog.add(resultTextField);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void avgHoaDon() {
        JDialog avgDialog = new JDialog(this, "Calculate Average Price");
        avgDialog.setLayout(new BorderLayout());

        // Panel chứa các thành phần nhập liệu
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel monthLabel = new JLabel("Enter Month (1-12): ");
        JTextField monthTextField = new JTextField(10);
        inputPanel.add(monthLabel);
        inputPanel.add(monthTextField);

        // Panel chứa nút tính toán
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton calculateButton = new JButton("Calculate");
        buttonPanel.add(calculateButton);

        // Thêm các panel vào JDialog
        avgDialog.add(inputPanel, BorderLayout.NORTH);
        avgDialog.add(buttonPanel, BorderLayout.CENTER);

        // Label hiển thị kết quả
        JLabel resultLabel = new JLabel("Average Price: ");
        JLabel resultTextLabel = new JLabel();
        resultTextLabel.setPreferredSize(new Dimension(100, 25));
        avgDialog.add(resultLabel, BorderLayout.WEST);
        avgDialog.add(resultTextLabel, BorderLayout.EAST);

        // Xử lý sự kiện khi nhấn nút Calculate
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String monthInput = monthTextField.getText();
                if (!monthInput.isEmpty()) {
                    try {
                        int month = Integer.parseInt(monthInput);
                        if (month >= 1 && month <= 12) {
                            double average = hoaDonService.avgByMonth(month);
                            if (average != 0.0) {
                                resultTextLabel.setText(String.valueOf(average));
                            } else {
                                JOptionPane.showMessageDialog(avgDialog, "No data available for the selected month.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(avgDialog, "Please enter a valid month (1-12).");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(avgDialog, "Please enter a valid number for month.");
                    }
                } else {
                    JOptionPane.showMessageDialog(avgDialog, "Please enter a valid month.");
                }
            }
        });

        avgDialog.pack();
        avgDialog.setLocationRelativeTo(this);
        avgDialog.setVisible(true);
    }

    private void loadHoaDon(){
        List<HoaDonTheoGio> hoaDonTheoGio = hoaDonService.getHoaDonTheoGio();
        List<HoaDonTheoNgay> hoaDonTheoNgay = hoaDonService.getHoaDonTheoNgay();
        tableModel.setRowCount(0); 

        for (int i = 0; i < Math.min(hoaDonTheoGio.size(), hoaDonTheoNgay.size()); i++) {
            HoaDonTheoGio hoaDonGio = hoaDonTheoGio.get(i);
            HoaDonTheoNgay hoaDonNgay = hoaDonTheoNgay.get(i);

            double thanhTien = 0.0;

            if (hoaDonGio != null) {
                thanhTien += hoaDonGio.thanhTien();
            } 
    
            if (hoaDonNgay != null) {
                thanhTien += hoaDonNgay.thanhTien();
            }

            Object[] rowData = {
                hoaDonGio.getId(),
                hoaDonGio.getDate(),
                hoaDonGio.getNameCustomer(),
                hoaDonGio.getRoomId(),
                hoaDonGio.getUnitPrice(),
                hoaDonNgay.getDay(),
                hoaDonGio.getHour(),
                thanhTien
            };
            tableModel.addRow(rowData);
        }
        JOptionPane.showMessageDialog(this, "List has been loaded.");
    }

    private void loadAllHoaDonList() {
        List<HoaDonTheoGio> hoaDonTheoGio = hoaDonService.getHoaDonTheoGio();
        List<HoaDonTheoNgay> hoaDonTheoNgay = hoaDonService.getHoaDonTheoNgay();
        tableModel.setRowCount(0); 

        for (int i = 0; i < Math.min(hoaDonTheoGio.size(), hoaDonTheoNgay.size()); i++) {
            HoaDonTheoGio hoaDonGio = hoaDonTheoGio.get(i);
            HoaDonTheoNgay hoaDonNgay = hoaDonTheoNgay.get(i);

            double thanhTien = 0.0;

            if (hoaDonGio != null) {
                thanhTien += hoaDonGio.thanhTien();
            } 
    
            if (hoaDonNgay != null) {
                thanhTien += hoaDonNgay.thanhTien();
            }

            Object[] rowData = {
                hoaDonGio.getId(),
                hoaDonGio.getDate(),
                hoaDonGio.getNameCustomer(),
                hoaDonGio.getRoomId(),
                hoaDonGio.getUnitPrice(),
                hoaDonNgay.getDay(),
                hoaDonGio.getHour(),
                thanhTien
            };
            tableModel.addRow(rowData);
        }
    }

    // Method to clear input fields
    private void clearFields() {
        idTextField.setText("");
        dateTextField.setText("");
        nameTextField.setText("");
        idroomTextField.setText("");
        unitpriceTextField.setText("");
        dayTextField.setText("");
        hourTextField.setText("");
    }

    

}
