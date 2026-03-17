package appli.professeur;

import appli.StartApplication;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.gestionnaire.Fourniture;
import repository.gestionnaire.FournitureRepository;

import java.io.IOException;
import java.util.List;

public class DemandeCreateController {

    public static class DemandeRow {

        private final SimpleIntegerProperty numero;
        private final SimpleStringProperty  fourniture;
        private final SimpleStringProperty  quantite;

        public DemandeRow(int numero) {
            this.numero     = new SimpleIntegerProperty(numero);
            this.fourniture = new SimpleStringProperty("");
            this.quantite   = new SimpleStringProperty("");
        }

        public int getNumero()                          { return numero.get(); }
        public SimpleIntegerProperty numeroProperty()   { return numero; }

        public String getFourniture()                       { return fourniture.get(); }
        public void setFourniture(String val)               { fourniture.set(val); }
        public SimpleStringProperty fournitureProperty()    { return fourniture; }

        public String getQuantite()                         { return quantite.get(); }
        public void setQuantite(String val)                 { quantite.set(val); }
        public SimpleStringProperty quantiteProperty()      { return quantite; }

        public boolean isFilled() {
            return !getFourniture().isEmpty() && !getQuantite().isEmpty();
        }
    }

    @FXML private TableView<DemandeRow>            demandeTable;
    @FXML private TableColumn<DemandeRow, Number>  numCol;
    @FXML private TableColumn<DemandeRow, String>  fournitureCol;
    @FXML private TableColumn<DemandeRow, String>  quantiteCol;

    private List<Fourniture> fournitures;

    private final ObservableList<DemandeRow> rows = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        FournitureRepository fournitureRepo = new FournitureRepository();
        fournitures = fournitureRepo.getAllFournitures();

        setupNumCol();
        setupFournitureCol();
        setupQuantiteCol();

        rows.add(new DemandeRow(1));
        demandeTable.setItems(rows);
    }

    private void setupNumCol() {
        numCol.setCellValueFactory(data -> data.getValue().numeroProperty());
        numCol.setStyle("-fx-alignment: CENTER;");
    }

    private void setupFournitureCol() {
        fournitureCol.setCellValueFactory(data -> data.getValue().fournitureProperty());

        fournitureCol.setCellFactory(col -> new TableCell<>() {

            private final MenuButton menuButton = new MenuButton("Sélectionner");

            {
                menuButton.setMaxWidth(Double.MAX_VALUE);

                for (Fourniture f : fournitures) {
                    MenuItem item = new MenuItem(f.getLibelle());
                    item.setOnAction(e -> {
                        menuButton.setText(f.getLibelle());
                        DemandeRow row = getTableRow().getItem();
                        if (row != null) {
                            row.setFourniture(f.getLibelle());
                            checkAndAddRow();
                        }
                    });
                    menuButton.getItems().add(item);
                }
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow().getItem() == null) {
                    setGraphic(null);
                } else {
                    String current = getTableRow().getItem().getFourniture();
                    menuButton.setText(current.isEmpty() ? "Sélectionner" : current);
                    setGraphic(menuButton);
                }
            }
        });
    }

    private void setupQuantiteCol() {
        quantiteCol.setCellValueFactory(data -> data.getValue().quantiteProperty());

        quantiteCol.setCellFactory(col -> new TableCell<>() {

            private final TextField textField = new TextField();

            {
                textField.setPromptText("Quantité");
                textField.setMaxWidth(Double.MAX_VALUE);

                textField.textProperty().addListener((obs, oldVal, newVal) -> {
                    if (!newVal.matches("\\d*")) {
                        textField.setText(newVal.replaceAll("[^\\d]", ""));
                    }
                });

                textField.focusedProperty().addListener((obs, wasFocused, isFocused) -> {
                    if (!isFocused) {
                        DemandeRow row = getTableRow().getItem();
                        if (row != null) {
                            row.setQuantite(textField.getText());
                            checkAndAddRow();
                        }
                    }
                });

                textField.setOnAction(e -> {
                    DemandeRow row = getTableRow().getItem();
                    if (row != null) {
                        row.setQuantite(textField.getText());
                        checkAndAddRow();
                    }
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow().getItem() == null) {
                    setGraphic(null);
                } else {
                    textField.setText(getTableRow().getItem().getQuantite());
                    setGraphic(textField);
                }
            }
        });
    }

    private void checkAndAddRow() {
        if (rows.isEmpty()) return;

        DemandeRow derniereLigne = rows.get(rows.size() - 1);
        if (derniereLigne.isFilled()) {
            rows.add(new DemandeRow(rows.size() + 1));
            demandeTable.scrollTo(rows.size() - 1);
        }
    }

    @FXML
    private void onDemanderClick() {
    }

    @FXML
    private void onAnnulerClick() {
        rows.clear();
        rows.add(new DemandeRow(1));
    }

    @FXML
    void onProfilClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("profil/profilRead", "Profil");
    }

    @FXML
    void onRetourClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/homePage", "Accueil");
    }
}