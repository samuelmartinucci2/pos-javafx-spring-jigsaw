package com.samuel.desktopclient;

import com.samuel.desktopclient.helper.DialogController;
import com.samuel.desktopclient.helper.FXMLDialog;
import com.samuel.service.CustomerService;
import com.samuel.service.dto.PersonDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class CustomerRegistrationFormController implements DialogController, Initializable {
    @Autowired
    private CustomerService customerService;
    private ScreensConfiguration screens;
    private FXMLDialog dialog;

    public void setDialog(final FXMLDialog dialog) {
        this.dialog = dialog;
    }

    public CustomerRegistrationFormController(final ScreensConfiguration screens) {
        this.screens = screens;
    }

    @FXML
    public TextField cpfField;

    @FXML
    public TextField nameField;

    @FXML
    public TextField emailField;

    @FXML
    public TextField addressField;

    @FXML
    public TextField postalCodeField;

    @FXML
    public TableView<PersonDto> TableView;

    @FXML
    public TableColumn<PersonDto, String> cpfColumn;

    @FXML
    public TableColumn<PersonDto, String> nameColumn;

    @FXML
    public TableColumn<PersonDto, String> emailColumn;

    @FXML
    public TableColumn<PersonDto, String> addressColumn;

    @FXML
    public TableColumn<PersonDto, String> postalCodeColumn;

    @FXML
    public TableColumn<PersonDto, Boolean> editColumn;

    @FXML
    public void saveButton() {
        final PersonDto person = PersonDto.builder()
                .cpf(cpfField.getText())
                .name(nameField.getText())
                .email(emailField.getText())
                .address(addressField.getText())
                .postalCode(postalCodeField.getText())
                .employee(false)
                .build();
        customerService.save(person);
        showCustomers();
    }

    @FXML
    public void resetButton() {
        cpfField.clear();
        nameField.clear();
        emailField.clear();
        addressField.clear();
        postalCodeField.clear();
    }

    @FXML
    public void deleteButton() {
        PersonDto user = TableView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmacao");
        alert.setHeaderText(null);
        alert.setContentText("Deseja excluir o item selecionado?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) customerService.remove(user.getCpf());

        showCustomers();
    }

    private void refresh(final PersonDto person) {
        cpfField.setText(person.getCpf());
        nameField.setText(person.getName());
        emailField.setText(person.getEmail());
        addressField.setText(person.getAddress());
        postalCodeField.setText(person.getPostalCode());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showCustomers();
    }

    public void showCustomers() {
        final ObservableList<PersonDto> list = FXCollections.observableArrayList();
        list.addAll(customerService.list());

        cpfColumn.setCellValueFactory(new PropertyValueFactory<PersonDto, String>("cpf"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<PersonDto, String>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<PersonDto, String>("email"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<PersonDto, String>("address"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<PersonDto, String>("postalCode"));
        editColumn.setCellFactory(new TableColumnTableCellCallback());

        TableView.setItems(list);
    }

    private class TableColumnTableCellCallback implements Callback<TableColumn<PersonDto, Boolean>, TableCell<PersonDto, Boolean>> {
        @Override
        public TableCell<PersonDto, Boolean> call(final TableColumn<PersonDto, Boolean> param) {
            return new TableCell<>() {
                final Button btnEdit = new Button();

                @Override
                public void updateItem(Boolean check, boolean empty) {
                    super.updateItem(check, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        final PersonDto person = getTableView().getItems().get(getIndex());
                        btnEdit.setOnAction(e -> refresh(person));
                        btnEdit.setText("Editar");
                        setGraphic(btnEdit);
                        setAlignment(Pos.CENTER);
                    }
                    setText(null);
                }
            };
        }
    }
}
