import util.Point3f;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseController implements MouseListener, MouseMotionListener {
    private static boolean MouseMoved = false;
    private static Point3f MoveTo;

    private static final MouseController instance = new MouseController();

    public MouseController() {
    }

    public boolean isMouseMoved() {
        return MouseMoved;
    }

    public Point3f getMoveTo() {
        return MoveTo;
    }

    public static MouseController getInstance(){
        return instance;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        MouseMoved = true;
        MoveTo = new Point3f(e.getX(), e.getY(), 0);
//        System.out.println("clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        System.out.println("pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MouseMoved = false;
//        System.out.println("released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        System.out.println("entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        System.out.println("exited");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MouseMoved = true;
        MoveTo = new Point3f(e.getX(), e.getY(), 0);
//        System.out.println("mouse dragged");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
