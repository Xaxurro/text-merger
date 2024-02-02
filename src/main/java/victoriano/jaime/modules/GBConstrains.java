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
}
