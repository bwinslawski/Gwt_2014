package e.g.com.example.mypr.client;

import java.util.ArrayList;
import java.util.List;

import e.g.com.example.mypr.shared.FieldVerifier;
import e.g.com.example.mypr.model.Img;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Nowyprojekt implements EntryPoint {

		  private ArrayList<Img> db = new ArrayList<Img>();
		 
		  private void addnew(){
			  Img i = new Img();
			  int ile = db.size();
			  i.setId(ile);
			  i.setPath("path");
			  i.setCountry("jnhgbf");
			  i.setWas(true);
			  db.add(i);
		  }
		  
		  public void addImg(String path){
			  Img i = new Img();
			  int ile = db.size();
			  i.setId(ile);
			  i.setPath(path);
			  i.setCountry("Polska");
			  i.setWas(true);
			  db.add(i);
		  }
		  public void updateIMG() {
			  String zdanie="";
			  String urlclose = "http://www.eng.mcmaster.ca/~selvaga/image/exit.png";
			  urlclose="";
			  for(int l=0;l<db.size();l++){
		zdanie=zdanie +"<li><a href=\""+db.get(l).getPath().toString()+"\" rel=\" prettyPhoto[gallery1]\"> "
	+"<img src=\""+db.get(l).getPath().toString()+"\" width=\"60\" height=\"60\" alt=\"Nice building\" /></a></li>";
					
				}
			  RootPanel.get("list").clear();
			  RootPanel.get("list").add(new HTML(zdanie));
		  }
		  public List<Img> getAllImg(){
			  return db;
		  }

	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);


	public void onModuleLoad() {
		final Button addIMG = new Button("Dodaj Zdjecie");
		final TextBox Img_url = new TextBox();
		Img_url.setText("url do zdjecia");
		final Label errorLabel = new Label();
		
		addIMG.addStyleName("sendButton");
		
		RootPanel.get("nameFieldContainer").add(Img_url);
		RootPanel.get("addButtonContainer").add(addIMG);
		RootPanel.get("errorLabelContainer").add(errorLabel);
		
		Img_url.setFocus(true);
		Img_url.selectAll();
	
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
	
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				addIMG.setEnabled(true);
				addIMG.setFocus(true);
				
			}
		});
		
		class HandlerIMG implements ClickHandler, KeyUpHandler {
			
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = Img_url.getText();
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}
				serverResponseLabel.setText("");
				addImg(textToServer);
				updateIMG();
				
			}
			
		}
		HandlerIMG handlerimg = new HandlerIMG();
		addIMG.addClickHandler(handlerimg);
		Img_url.addKeyUpHandler(handlerimg);
	}
}
