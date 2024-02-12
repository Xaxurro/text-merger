package victoriano.jaime.modules;

import java.awt.*;

//Esta clase debe darme un constructor para manejarlo mas facil
public class GBConstrains extends GridBagConstraints {

    public GBConstrains(int x, int y, int width, int height) {
        super();
        this.gridx = x;
        this.gridy = y;
        this.gridwidth = width;
        this.gridheight = height;
    }

    public GBConstrains(int x, int y) {
        super();
        this.gridx = x;
        this.gridy = y;
        this.gridwidth = 1;
        this.gridheight = 1;
    }

    public GBConstrains expandHorizontally() {
        this.fill = GridBagConstraints.HORIZONTAL;
        return this;
    }

    public GBConstrains expandVertically() {
        this.fill = GridBagConstraints.VERTICAL;
        return this;
    }

    public GBConstrains expandBothAxis() {
        this.fill = GridBagConstraints.BOTH;
        return this;
    }

    public GBConstrains align(int direction) {
//        GridBagConstraints tiene constantes para alinear los componentes, sus ids estan numerados del 11 al 18, los mios del 1 al 8, asi que solo sumo 10
        this.anchor = direction;
        return this;
    }

    public GBConstrains setInsets(int t, int l, int b, int r) {
        this.insets = new Insets(t, l, b, r);
        return this;
    }
}
