import java.util.HashSet;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

// An alternative implementation of Example 3,
//    using the Timeline, KeyFrame, and Duration classes.

// Animation of Earth rotating around the sun. (Hello, world!)
public class project extends Application 
{
	static HashSet<String> currentlyActiveKeys;
	
	public static void main(String[] args) 
    {
        launch(args);
    }
	
	Group root; 
	Group sub;
	Scene theScene; 
	Scene firstScene;
	//Parent test = FXMLLoader.load(project.class.getResource("root.fxml"));
	
	Canvas canvas = new Canvas( 512, 512 );
	Canvas subcanvas = new Canvas( 512, 512 );
	GraphicsContext gc = canvas.getGraphicsContext2D();
	GraphicsContext subgc = subcanvas.getGraphicsContext2D();
	
	int ck=2;
    double x = 232+128;
    double y = 232;
    double t = 0;
    double tt = 0;
    double a = 232-128;
    double b = 232;
    Image tank1 = new Image( "tank1.png" );
    Image bull = new Image("bullseye.png");
    Image space = new Image( "space.png" );
    Image sun   = new Image( "sun.png" );
    Image bullseye = new Image( "bullseye.png" );
    boolean turn;
    boolean gogo=!true;
    
    @Override
    public void start(Stage theStage) 
    {
    	root = new Group();
    	sub = new Group();
    	theScene =  new Scene( root );
    	firstScene = new Scene(sub);
    	//firstScene.setFill();
    	
    	//Button button = new Button();
    	//button.setText("START");
    	//button.setPrefSize(100, 100);
    	//button.setOnAction(event->Platfrom.exit());
    	
        theStage.setTitle( "포트리스 프로젝트" );

        prepareActionHandlers();
        Circle targetData = new Circle(230,400,40);

        if(!gogo) {
        	theStage.setScene( firstScene );
        	sub.getChildren().add(subcanvas);
        	//gc.drawImage(space,0,0);
        	//sub.getChildren().add(button);
        	//button.setOnAction(event->{
        	//	theStage.setScene(theScene);
        	//});
            	subgc.setFill( new Color(0.85, 0.85, 1.0, 1.0) );
                subgc.fillRect(0,0, 512,512);
            	//System.out.println("dd");
                subgc.drawImage(bullseye,211,400);
                
                Font theFont = Font.font( "consolas", FontWeight.BOLD, 30 );
                subgc.setFont( theFont );
                subgc.fillText( "객체지향 프로젝트\n 과녁을 맞추라우", 120, 50 );
                subgc.strokeText( "객체지향 프로젝트\n 과녁을 맞추라우", 120, 50 );
            	firstScene.setOnMouseClicked(
                           new EventHandler<MouseEvent>()
                           {
                               public void handle(MouseEvent e)
                               {
                            	   System.out.println("dd");
                            	  if ( targetData.contains( e.getX(), e.getY() ) )
                                  {
                            		  theStage.setScene(theScene);
                            		  System.out.println("hello");
                                  }
                               }
                           });
        	//gogo = true;
        }
        
        root.getChildren().add( canvas );
        //root.getChildren().add(button);
        //System.out.println(1);
        
        
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );
        
        final long timeStart = System.currentTimeMillis();
        
        //gc.fillText( "Hello, World!", 60, 150 );
        //gc.strokeText( "Hello, World!", 60, 150 );
        
        
        KeyFrame kf = new KeyFrame(
            Duration.seconds(0.017),                // 60 FPS
            new EventHandler<ActionEvent>()
            {
            	
            	public void handle(ActionEvent ae)
                {
                    //t = (System.currentTimeMillis() - timeStart) / 1000.0; 
                    //t=0;            
                    //x = 232; //+ 128 * Math.cos(t);
                    //y = 232; //+ 128 * Math.sin(t);
                    tickAndRender();
                    // Clear the canvas
                    
                    gc.clearRect(0, 0, 512,512);
                    
                    // background image clears canvas
                    gc.drawImage( space, 0, 0 );
                    gc.drawImage( tank1, x, y );
                    gc.drawImage( sun, 196, 196 );
                    gc.drawImage(bull,a,b);
                    
                    gc.setFill( Color.RED );
                    gc.setStroke( Color.WHITE );
                    Font theFont = Font.font( "consolas", FontWeight.BOLD, 30 );
                    gc.setFont( theFont );
                    gc.fillText( "Press Enter to Change Turn", 44, 50 );
                    gc.strokeText( "Press Enter to Change Turn", 44, 50 );
                }
            });
        
        gameLoop.getKeyFrames().add( kf );
        gameLoop.play();
        
        theStage.show();
    }
    
    private void prepareActionHandlers()
    {
        // use a set so duplicates are not possible
        currentlyActiveKeys = new HashSet<String>();
        theScene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                currentlyActiveKeys.add(event.getCode().toString());
            }
        });
        theScene.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                currentlyActiveKeys.remove(event.getCode().toString());
            }
        });
    }
    
    private void tickAndRender()
    {
        // clear canvas
        //graphicsContext.clearRect(0, 0, WIDTH, HEIGHT);
    	gc.clearRect(0, 0, 512,512);
    	
        //System.out.println(1);
    	if(currentlyActiveKeys.contains("ENTER")) {
    		
    			ck+=1;
    			if(ck<2) {
    				turn=!turn;
    			}
    			
    	}
    	else if (currentlyActiveKeys.contains("LEFT"))
        {
    		ck=0;
        	//System.out.println("1111111");
        	if(!turn) {
        		t-=0.02;
            	x = 232 + 128 * Math.cos(t);
                y = 232 + 128 * Math.sin(t);
        	}
        	else {
        		tt-=0.02;
            	a = 232 - 128 * Math.cos(-tt);
                b = 232 + 128 * Math.sin(-tt);
        	}
        	
        	gc.drawImage( space, 0, 0 );
           gc.drawImage( tank1, x, y );
           gc.drawImage( sun, 196, 196 );
           gc.drawImage(bull,a,b);
           //gc.fillText( "Hello, World!", 60, 50 );
           //gc.strokeText( "Hello, World!", 60, 50 );
        }
        else
        {
            //System.out.println("dpdpdpddp");
        	//graphicsContext.drawImage(left, 64 ,64);
        	gc.drawImage( space, 0, 0 );
            gc.drawImage( tank1, x, y );
            gc.drawImage( sun, 196, 196 );
            gc.drawImage(bull,a,b);
            //gc.fillText( "Hello, World!", 60, 50 );
            //gc.strokeText( "Hello, World!", 60, 50 );
        }

        if (currentlyActiveKeys.contains("RIGHT"))
        {
        	ck=0;
        	if(!turn) {
        		t+=0.02;
            	x = 232 + 128 * Math.cos(t);
                y = 232 + 128 * Math.sin(t);
        	}
        	else {
        		tt+=0.02;
        		a = 232 - 128 * Math.cos(-tt);
                b = 232 + 128 * Math.sin(-tt);
        	}
        	gc.drawImage( space, 0, 0 );
           gc.drawImage( tank1, x, y );
           gc.drawImage( sun, 196, 196 );
           gc.drawImage(bull,a,b);
           //gc.fillText( "Hello, World!", 60, 50 );
           //gc.strokeText( "Hello, World!", 60, 50 );
        }
        else
        {
            //System.out.println("ffffffffffff");
        	//graphicsContext.drawImage(right, 320, 64);
        	gc.drawImage( space, 0, 0 );
            gc.drawImage( tank1, x, y );
            gc.drawImage( sun, 196, 196 );
            gc.drawImage(bull,a,b);
            //gc.fillText( "Hello, World!", 60, 50 );
            //gc.strokeText( "Hello, World!", 60, 50 );
        }
    }
}