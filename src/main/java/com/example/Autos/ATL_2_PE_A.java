package com.example.Autos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;

import javax.servlet.annotation.WebServlet;
import javax.swing.plaf.TableUI;
import javax.swing.text.TableView;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.provider.CallbackDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.TabSheet.Tab;

import java.util.stream.*;



/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("Autos")
public class ATL_2_PE_A extends UI 
{
	
	public static List<CarObj> Autolist = new ArrayList<>() ;
	public static String suche = "";

    @Override
    protected void init(VaadinRequest vaadinRequest) 
    {
    	
    
    	
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField txtSuche = new TextField();
        txtSuche.setCaption("Suche nach dem Modell");

        Button btnSuche = new Button("Suchen");
        
                  	   
        		btnSuche.addClickListener(e -> 
               {
            	   suche = txtSuche.getValue();            	 
                   searchCar();
               });
                
               try 
               {
					loadCSV();
               } catch (IOException e1) 
               {
					// TODO Auto-generated catch block
					e1.printStackTrace();
               }

					Grid<CarObj> grid = new Grid<>(CarObj.class);
					grid.setSizeFull();									         		            			            			            	
					
					for(CarObj ausgabe : Autolist)
					{						
						grid.setItems(Autolist);
						
					}
										                      

        layout.addComponents(txtSuche, btnSuche, grid);
        
        setContent(layout);
        
    }
    
    
    
    /*
     * Lädt die "Datenbank" in die Objekte.
     */
    public void loadCSV() throws IOException
    {
    	String csvDat = "src/main/java/com/example/Autos/cars.csv";
    	
    	InputStream iS = new FileInputStream(new File(csvDat));
        BufferedReader bR = new BufferedReader(new InputStreamReader(iS));

        Function<String, CarObj> mapToCar = (line) ->
        {
            String[] p = line.split(";");
            return new CarObj(p[0], p[1], p[2], Integer.parseInt(p[3]), Integer.parseInt(p[4]));
        };
        
        
        //  Liste zusammenfassen und den Header überspringen.
        List<CarObj> cars = bR.lines()
                .skip(1)
                .map(mapToCar)
                .limit(100)
                .collect(Collectors.toList());
                
        Autolist = cars;
    }
    
    /*
     *Hier wird nach dem Modell welches in der Suche eingegeben wurde gesucht und nur dieses wird dann ind er Liste angezeigt. 
     *
     */
    public void searchCar()
    {
    	 for(CarObj d : Autolist)
    	 {
    	        if(d.getModell() != null && d.getModell().contains(suche))
    	        {
    	        	List<CarObj> found = new ArrayList();
    	        	
    	        	found.add(d);
    	        	
    	        	Autolist = found;
    	        	break;
    	        }

    	    }
    }
   
    

    @WebServlet(urlPatterns = "/*", name = "ATL_2_PE_AServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = ATL_2_PE_A.class, productionMode = false)
    public static class ATL_2_PE_AServlet extends VaadinServlet 
    {
    }
}
