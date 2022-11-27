import processing.core.*;

public class GUI extends PApplet {
    Simulator sim;
    DisplayWindow gridDisplay;
    int timer = 0;

    public void settings() {
        size(620, 620); // set the size of the screen.
    }

    public void setup() {
        sim = new Simulator(100, 100);
        sim.initializeTrees(50);
        sim.startFire(1);
       // sim.createDeadTreeRow();

        gridDisplay = new DisplayWindow(this, 10, 10, 620, 620);

        gridDisplay.setNumCols(sim.getWidth());
        gridDisplay.setNumRows(sim.getHeight());

        gridDisplay.setColor(Simulator.ON_FIRE, color(255, 0, 0));
        gridDisplay.setColor(Simulator.ASH, color(180));
        gridDisplay.setColor(Simulator.LIVING, color(0, 255, 0));
        gridDisplay.setColor(Simulator.EMPTY, color(255));
    }

    @Override
    public void draw() {
        background(200);
        timer++;
        if(timer == 3) {
            sim.propagateFire();
            timer = 0;
        }

        gridDisplay.drawGrid(sim.getDisplayGrid());
    }

    public static void main(String[] args) {
        PApplet.main("GUI");
    }
}