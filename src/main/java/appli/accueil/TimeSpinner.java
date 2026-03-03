package appli.accueil;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.LocalTimeStringConverter;

import java.time.LocalTime;

public class TimeSpinner extends Spinner<LocalTime> {

    private enum Field { HOUR, MINUTE, SECOND }
    private Field activeField = Field.MINUTE;

    public TimeSpinner() {
        super();
        setValueFactory(new TimeSpinnerValueFactory());
        getEditor().setTextFormatter(new TextFormatter<>(new LocalTimeStringConverter()));

        // Détection du champ sélectionné dans l’éditeur
        getEditor().focusedProperty().addListener((obs, oldV, newV) -> {
            if (newV) updateActiveField();
        });

        getEditor().caretPositionProperty().addListener((obs, oldV, newV) -> updateActiveField());
    }



    private void updateActiveField() {
        int caret = getEditor().getCaretPosition();
        String text = getEditor().getText();

        if (caret <= text.indexOf(':')) activeField = Field.HOUR;
        else if (caret <= text.lastIndexOf(':')) activeField = Field.MINUTE;
        else activeField = Field.SECOND;
    }

    private class TimeSpinnerValueFactory extends SpinnerValueFactory<LocalTime> {

        public TimeSpinnerValueFactory() {
            setValue(LocalTime.now());
        }

        @Override
        public void decrement(int steps) {
            LocalTime t = getValue();
            switch (activeField) {
                case HOUR -> setValue(t.minusHours(steps));
                case MINUTE -> setValue(t.minusMinutes(steps));
                case SECOND -> setValue(t.minusSeconds(steps));
            }
        }

        @Override
        public void increment(int steps) {
            LocalTime t = getValue();
            switch (activeField) {
                case HOUR -> setValue(t.plusHours(steps));
                case MINUTE -> setValue(t.plusMinutes(steps));
                case SECOND -> setValue(t.plusSeconds(steps));
            }
        }
    }
}
