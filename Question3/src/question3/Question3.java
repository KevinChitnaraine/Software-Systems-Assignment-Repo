import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.geometry.Insets;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
//Package to handle events

public class Question3 extends Application
{
    public void start(Stage primaryStage){
        final Triangle PP = new Triangle(640, 480);
        BorderPane borderPane = new BorderPane(PP);
        Scene scene = new Scene(borderPane);
        primaryStage.setTitle("Question3");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class Triangle extends Pane{
        final Circle crl = new Circle();
        final vertex[] vtx = new vertex[3];
        final int strokeWidth = 2;
        final Color CS = Color.BLACK, LS = Color.BLACK;

        Triangle(double width, double height){
            this.setPrefSize(width, height);
            this.setWidth(width);
            this.setHeight(height);

            crl.setStroke(CS);
            crl.setFill(Color.TRANSPARENT);
            crl.setStrokeWidth(strokeWidth);
            crl.radiusProperty().bind(this.heightProperty().multiply(0.3));
            crl.centerXProperty().bind(this.widthProperty().divide(2));
            crl.centerYProperty().bind(this.heightProperty().divide(2));
            this.getChildren().add(crl);

            for (int k = 0; k < vtx.length; k++){
                vtx[k] = new vertex(crl, 2 * Math.PI / vtx.length * (k + Math.random()));
                vtx[k].radiusProperty().bind(crl.radiusProperty().divide(8));
                vtx[k].startPosition();
                vtx[k].setFill(Color.RED);
                vtx[k].setStrokeWidth(strokeWidth);
                this.getChildren().add(vtx[k]);

                vtx[k].setOnMouseDragged(new EventHandler<MouseEvent>(){
                    
                    public void handle(MouseEvent e){
                        int k;
                        for (k = 0; k < vtx.length; k++)
                        if (vtx[k] == e.getSource())
                            break;
                        vtx[k].startAngle(e.getX(), e.getY());
                        updateMove((vertex) e.getSource());
                    }
                });
            }

            for (int k = 0; k < vtx.length; k++){
                int m = k + 1 < vtx.length ? k + 1 : 0;
                int n = m + 1 < vtx.length ? m + 1 : 0;
                vtx[k].bdLg(vtx[m], vtx[n]);
                vtx[k].L.setStroke(LS);
                vtx[k].L.setStrokeWidth(strokeWidth);
                this.getChildren().add(vtx[k].L);
                this.getChildren().add(vtx[k].txt);
            }

            for(DoubleProperty DP: new DoubleProperty[]
            {crl.radiusProperty(), crl.centerXProperty(), crl.centerYProperty()})
            DP.addListener(new RL());

            updateMove(vtx[0]);
        }

        void updateMove(vertex V){
            V.startPosition();
            double[] Angle = new double[3];
            for (int k = 0; k < vtx.length; k++)
            Angle[k] = vtx[k].getAngle();

            for(int k = 0; k < vtx.length; k++){
                int m = k + 1 < vtx.length ? k + 1 : 0;
                int n = m + 1 < vtx.length ? m + 1 : 0;
                double x = Angle[k], y = Angle[m], z = Angle[n];
                double deg = Math.toDegrees(Math.acos((x * x - y * y - z * z) / (-2 * y * z)));
                vtx[k].startText(deg);
            }
        }

        private class RL implements ChangeListener<Number>{

            public void changed(ObservableValue<? extends Number> OV, Number OW, Number NW){
                for (vertex V : vtx){
                    V.startPosition();
                }
            }
        }
    }

    private class vertex extends Circle{
        final Circle crl;
        final Line L;
        final Text txt;
        double centreAngle;

        vertex(Circle crl, double centreAngle){
            this.crl = crl;
            this.startAngle(centreAngle);
            this.L = new Line();
            this.txt = new Text();
            this.txt.setFont(Font.font(20));
            this.txt.setStroke(Color.BLACK);
            this.txt.setTextAlignment(TextAlignment.CENTER);
            this.txt.xProperty().bind(this.centerXProperty().add(25));
            this.txt.yProperty().bind(this.centerYProperty().subtract(10));
        }

        double getcentreAngle() {return this.centreAngle;}

        void startPosition(){
            this.setCenterX(crl.getCenterX() + crl.getRadius() * Math.cos(this.getcentreAngle()));
            this.setCenterY(crl.getCenterY() + crl.getRadius() * Math.sin(this.getcentreAngle()));
        }

        void startAngle(double centreAngle){
            this.centreAngle = centreAngle;
        }

        void startAngle(double a, double b){
            this.startAngle(Math.atan2(b - crl.getCenterY(), a - crl.getCenterX()));
        }

        void bdLg(vertex vertex1, vertex vertex2){
            L.startXProperty().bind(vertex1.centerXProperty());
            L.startYProperty().bind(vertex1.centerYProperty());
            L.endXProperty().bind(vertex2.centerXProperty());
            L.endYProperty().bind(vertex2.centerYProperty());
        }

        double getAngle(){
            return Math.sqrt(Math.pow(L.getStartX()-L.getEndX(),2) + Math.pow(L.getStartY()-L.getEndY(),2));
        }

        void startText(double ang){
            this.txt.setText(String.format("%.0f\u00B0", ang));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}