package fxml;

import actividad.Mapa;

public interface ControlledScreen {
    
    //This method will allow the injection of the Parent ScreenPane
    public void setScreenParent(ScreensController screenParent);
    public void addMapa(Mapa mapa);
}
