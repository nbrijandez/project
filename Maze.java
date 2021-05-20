import javafx.event.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.canvas.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.geometry.*;
import javafx.stage.*;
import java.util.*;
import java.io.*;
import javafx.scene.paint.Color;


public class Maze extends Application
{
   FlowPane root = new FlowPane();
   Canvas canvas = new Canvas();
   GraphicsContext gc = canvas.getGraphicsContext2D();   
   
   int [][] Maze = new int[21][21];
   
   private int xPos = 225, yPos = 0;
   
   private boolean move = true;
   
   public void start(Stage stage)
   {
      canvas.setWidth(525);
      canvas.setHeight(525);
      
      root.getChildren().add(canvas);
      
      try( Scanner scan = new Scanner (new File("MazeBoardFile.txt")))
      {
         for (int j = 0; j < 21; j++)
         {
           for (int i = 0; i < 21; i++)
           {
             Maze[i][j] = scan.nextInt();
           }
         }
      }
      catch ( FileNotFoundException fnfe) //catches error if file does not exist
      {
         System.out.println("unable to find file, exiting");
      }
      
            
      draw();
      
      root.setOnKeyPressed(new KeyListenerDown());

      
      Scene scene = new Scene(root, 525,525);
      stage.setScene(scene);
      stage.setTitle("Maze Game");      
      stage.show(); 
      
      root.requestFocus();

   }
   
   public static void main(String [] args)
   {
      launch(args);
   }
   
   public void draw()
   {
      for (int i = 0; i < 21; i++)
      {
         for (int j = 0; j < 21; j++)
         {
            if (Maze[i][j] == 1)
            {
               gc.setFill(Color.BLACK);
               gc.fillRect(i*25,j*25,25, 25);
            }
            else if (Maze[i][0] == 0)
            {
               gc.setFill(Color.PAPAYAWHIP);
               xPos = i*25;
               gc.fillRect(xPos,yPos,25, 25);
            }
            else             {
               gc.setFill(Color.WHITE);
               gc.fillRect(i*25,j*25,25, 25);
            }
        }
      }
      gc.setFill(Color.MAGENTA);
      gc.fillRect(xPos,yPos,25, 25);
      
   }
   
   public class KeyListenerDown implements EventHandler<KeyEvent>
   {
      public void handle(KeyEvent event)
      {
         //gets what key was pressed
         KeyCode keyPressed = event.getCode();
         gc.clearRect(xPos, yPos, 25,25);

         if (event.getCode() == KeyCode.UP)
         {
            if(Maze[xPos/25][yPos/25 -1] == 0)
            {
               yPos = yPos - 25;
            }
            else
            {
               gc.fillRect(xPos,yPos,25, 25);
            }
         }
         if(event.getCode() == KeyCode.DOWN)
         {
            if(Maze[xPos/25][(yPos/25)+1] == 0)
            {
            yPos = yPos + 25;
            }
            else
            {
               gc.fillRect(xPos,yPos,25, 25);
            }


         }
         if(event.getCode() == KeyCode.LEFT)
         {
            if(Maze[xPos/25-1][yPos/25] == 0)
            {
            xPos -= 25;
            }
            else
            {
               gc.fillRect(xPos,yPos,25, 25);
            }


         }
         if(event.getCode() == KeyCode.RIGHT)
         {
            if(Maze[xPos/25+1][yPos/25] == 0)
            {
            xPos = xPos + 25;
            }
            else
            {
               gc.fillRect(xPos,yPos,25, 25);
            }
         }

        gc.fillRect(xPos,yPos,25, 25);
        
        if (yPos/25 == 20)
        {
            if(Maze[xPos/25][yPos/25] == 0)
            {
               System.out.println("you win");
            }
        }
      }
     }
   }