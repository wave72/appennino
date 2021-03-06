package it.burningboots.appennino.client.frame;

import it.burningboots.appennino.client.AuthSingleton;
import it.burningboots.appennino.client.ClientConstants;
import it.burningboots.appennino.client.CookieSingleton;
import it.burningboots.appennino.client.IAuthenticatedWidget;
import it.burningboots.appennino.client.UriBuilder;
import it.burningboots.appennino.client.widgets.DataModel;
import it.burningboots.appennino.client.widgets.DiscountTable;
import it.burningboots.appennino.client.widgets.ParticipantTable;
import it.burningboots.appennino.shared.entity.Discount;
import it.burningboots.appennino.shared.entity.Participant;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ParticipantFrame extends FramePanel implements IAuthenticatedWidget {
	
	private String filterConfirmed = "1";
	
	private VerticalPanel panel = null;
	private FlowPanel resultPanel = null;
	
	public ParticipantFrame(UriBuilder params) {
		super();
		if (params == null) {
			params = new UriBuilder();
		}
		String fc = CookieSingleton.get().getCookie(ClientConstants.COOKIE_FILTER_CONFIRMED);
		if (fc != null) {
			if (!fc.equals(""))	filterConfirmed = fc;
		}
		
		this.setWidth("100%");
		AuthSingleton.get().queueForAuthentication(this);
	}
	
	@Override
	public void onSuccessfulAuthentication() {
		draw();
	}
	
	private void draw() {
		setTitle("Participants");
		panel = new VerticalPanel();
		this.add(panel);
		// Select
		HorizontalPanel topPanel = new HorizontalPanel();
		topPanel.add(new HTML("Mostra:&nbsp;"));
		final ListBox confirmList = new ListBox();
		confirmList.addItem("Tutti","0");
		confirmList.addItem("Solo i confermati", "1");
		if (filterConfirmed.equals("0")) {
			confirmList.setSelectedIndex(0);
		} else {
			confirmList.setSelectedIndex(1);
		}
		confirmList.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				filterConfirmed = confirmList.getSelectedValue();
				CookieSingleton.get().setCookie(ClientConstants.COOKIE_FILTER_CONFIRMED,
						filterConfirmed);
				refreshTable();
			}
		});
		confirmList.setEnabled(true);
		topPanel.add(confirmList);
		panel.add(topPanel);
		//Tabella partecipanti
		resultPanel = new FlowPanel();
		panel.add(resultPanel);
		refreshTable();
		//Tabella low income
		panel.add(new HTML("<p>&nbsp;<br /><b>Low income address list</b></p>"));
		DataModel<Discount> discModel = new DiscountTable.DiscountModel();
		DiscountTable discTable = new DiscountTable(discModel);
		panel.add(discTable);
	}
	
	private void refreshTable() {
		resultPanel.clear();
		//Tabella
		DataModel<Participant> model = new ParticipantTable.ParticipantModel(filterConfirmed.equals("1"));
		ParticipantTable partTable = new ParticipantTable(model);
		resultPanel.add(partTable);
	}
	
}
