package it.burningboots.appennino.client.frame;

import it.burningboots.appennino.client.LocaleConstants;
import it.burningboots.appennino.client.UiSingleton;
import it.burningboots.appennino.client.UriBuilder;
import it.burningboots.appennino.client.UriDispatcher;
import it.burningboots.appennino.client.WaitSingleton;
import it.burningboots.appennino.client.WizardSingleton;
import it.burningboots.appennino.client.service.DataService;
import it.burningboots.appennino.client.service.DataServiceAsync;
import it.burningboots.appennino.client.widgets.WizardButtons;
import it.burningboots.appennino.shared.AppConstants;
import it.burningboots.appennino.shared.StringValidator;
import it.burningboots.appennino.shared.ValidationException;
import it.burningboots.appennino.shared.entity.Participant;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ReplaceBaseFrame extends FramePanel implements IWizardPanel {
	
	private final DataServiceAsync dataService = GWT.create(DataService.class);
	private LocaleConstants constants = GWT.create(LocaleConstants.class);
	
	private UriBuilder params = null;
	private VerticalPanel cp = null; // Content panel
	
	private TextBox codeText;
	private TextBox emailText;
	private String itemNumber = null;
	private String email = null;
	
	public ReplaceBaseFrame(UriBuilder params) {
		super();
		if (params != null) {
			this.params = params;
		} else {
			this.params = new UriBuilder();
		}
		String itemNumber = this.params.getValue(AppConstants.PARAMS_ITEM_NUMBER);
		if (itemNumber == null) itemNumber = "";
		cp = new VerticalPanel();
		this.add(cp);
		draw();
	}
	
	private void draw() {
		if (WizardSingleton.get().getWizardType().equals(AppConstants.WIZARD_REGISTER))
				forwardIfJoinNotPossible();
		Participant participant = WizardSingleton.get().getParticipantBean();
		if (participant == null) participant = new Participant();
		
		//TITLE
		setTitle(constants.replaceBaseTitle());
		
		cp.add(new HTML("<p>"+constants.replaceBaseWelcome()+"</p>"));
		cp.add(new HTML(constants.replacementCode()));
		codeText = new TextBox();
		codeText.setValue(participant.getItemNumber());
		cp.add(codeText);
		
		cp.add(new HTML("<p>&nbsp;</p>"));
		
		
		cp.add(new HTML("<p>"+constants.joinBaseEmail()+"</p>"));
		emailText = new TextBox();
		emailText.setValue(participant.getEmail());
		cp.add(emailText);
		cp.add(new HTML("<p><i>"+constants.joinBaseEmailWarning()+"</i></p>"));
		
		//Wizard panel
		WizardButtons wb = new WizardButtons(this, false, true);
		cp.add(wb);
	}
	
	@Override
	public void goBackward() {
		/* disabled */
	}
	
	@Override
	public void goForward() {
		//Verification
		String errorMessage = "";
		itemNumber = codeText.getValue();
		try {
			StringValidator.validateItemNumber(itemNumber);
		} catch (ValidationException e) {
			if (errorMessage.length() > 0) errorMessage += "<br />";
			errorMessage += e.getMessage();
		}
		email = emailText.getValue();
		try {
			StringValidator.validateEmail(email);
		} catch (ValidationException e) {
			if (errorMessage.length() > 0) errorMessage += "<br />";
			errorMessage += e.getMessage();
		}
		
		if (errorMessage.length() > 0) {
			UiSingleton.get().addWarning(errorMessage);
		} else {
			loadAsyncData(itemNumber);
		}
	}

	
	
	//Async methods
	
	
	private void loadAsyncData(String itemNumber) {
		AsyncCallback<Participant> callback = new AsyncCallback<Participant>() {
			@Override
			public void onFailure(Throwable caught) {
				UiSingleton.get().addError(caught);
				WaitSingleton.get().stop();
			}
			@Override
			public void onSuccess(Participant result) {
				if (result == null) result = new Participant();
				if (result.getEmail() == null) result.setEmail("");
				if (result.getEmail().equals("")) {
					// NOT FOUND
					UiSingleton.get().addWarning(constants.replaceBaseErrorReplacement());
				} else {
					// ITEM NUMBER FOUND
					UiSingleton.get().addInfo(constants.replaceBaseOkReplacement());
					WizardSingleton.get().setParticipantBean(result);
					//Store in bean
					result.setEmail(email);
					//Forward
					UriBuilder param = new UriBuilder();
					param.add(AppConstants.PARAMS_ITEM_NUMBER, result.getItemNumber());
					param.triggerUri(UriDispatcher.STEP_JOIN_VOLUNTEER);
				}
				WaitSingleton.get().stop();
			}
		};
		WaitSingleton.get().start();
		dataService.findParticipantByItemNumber(itemNumber, 0, callback);
	}
	
}
