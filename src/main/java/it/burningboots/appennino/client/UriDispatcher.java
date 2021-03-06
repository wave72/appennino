package it.burningboots.appennino.client;

import it.burningboots.appennino.client.frame.ErrorClosedFrame;
import it.burningboots.appennino.client.frame.ErrorFullFrame;
import it.burningboots.appennino.client.frame.ErrorPaymentFrame;
import it.burningboots.appennino.client.frame.JoinBaseFrame;
import it.burningboots.appennino.client.frame.JoinCheckoutFrame;
import it.burningboots.appennino.client.frame.JoinFoodFrame;
import it.burningboots.appennino.client.frame.JoinLegalFrame;
import it.burningboots.appennino.client.frame.JoinThankYouFrame;
import it.burningboots.appennino.client.frame.JoinVolunteerFrame;
import it.burningboots.appennino.client.frame.ParticipantFrame;
import it.burningboots.appennino.client.frame.ReplaceBaseFrame;
import it.burningboots.appennino.client.frame.ReplaceSaveFrame;
import it.burningboots.appennino.shared.AppConstants;

import com.google.gwt.user.client.ui.SimplePanel;

public class UriDispatcher {
	
	public static final String SEPARATOR_TOKEN = "!";// Â£
	public static final String SEPARATOR_PARAMS = "/"; // /
	public static final String SEPARATOR_VALUES = "=";
	
	public static final String INDEX = "index";
	public static final String STEP_JOIN_BASE = "base";
	public static final String STEP_JOIN_LEGAL = "legal";
	public static final String STEP_JOIN_VOLUNTEER = "volunteer";
	public static final String STEP_JOIN_FOOD = "food";
	public static final String STEP_JOIN_CHECKOUT = "checkout";
	public static final String ERROR_FULL = "errFull";
	public static final String ERROR_CLOSED = "errClosed";
	public static final String ERROR_PAYMENT = "errPayment";
	public static final String STEP_THANK_YOU = "thankyou";
	public static final String PARTICIPANTS = "participants";
	public static final String STEP_REPLACE_BASE = "replace";
	public static final String STEP_REPLACE_SAVE = "replaceSave";
	
	//Reloads the current page from the server reload(true) and not from cache reload(false)
	public static native void hardReload() /*-{
	  $wnd.location.reload(true);
	}-*/;
	
	public static void loadContent(String fullToken) {
		if (fullToken != null) {
			String token = tokenFromUri(fullToken);
			UriBuilder params = paramsFromUri(fullToken);
			SimplePanel contentPanel = UiSingleton.get().getContentPanel();
			contentPanel.clear();
			if (INDEX.equals(token)) {
				WizardSingleton.get().setWizardType(AppConstants.WIZARD_REGISTER);
				contentPanel.add(new JoinBaseFrame(params));
			}
			if (STEP_JOIN_BASE.equals(token)) {
				contentPanel.add(new JoinBaseFrame(params));
			}
			if (STEP_JOIN_LEGAL.equals(token)) {
				contentPanel.add(new JoinLegalFrame(params));
			}
			if (STEP_JOIN_FOOD.equals(token)) {
				contentPanel.add(new JoinFoodFrame(params));
			}
			if (STEP_JOIN_VOLUNTEER.equals(token)) {
				contentPanel.add(new JoinVolunteerFrame(params));
			}
			if (STEP_JOIN_CHECKOUT.equals(token)) {
				contentPanel.add(new JoinCheckoutFrame(params));
			}
			if (ERROR_FULL.equals(token)) {
				contentPanel.add(new ErrorFullFrame(params));
			}
			if (ERROR_CLOSED.equals(token)) {
				contentPanel.add(new ErrorClosedFrame(params));
			}
			if (ERROR_PAYMENT.equals(token)) {
				contentPanel.add(new ErrorPaymentFrame(params));
			}
			if (STEP_THANK_YOU.equals(token)) {
				contentPanel.add(new JoinThankYouFrame(params));
			}
			if (PARTICIPANTS.equals(token)) {
				contentPanel.add(new ParticipantFrame(params));
			}
			if (STEP_REPLACE_BASE.equals(token)) {
				WizardSingleton.get().setWizardType(AppConstants.WIZARD_TRANSFER);
				contentPanel.add(new ReplaceBaseFrame(params));
			}
			if (STEP_REPLACE_SAVE.equals(token)) {
				contentPanel.add(new ReplaceSaveFrame(params));
			}
		}
	}
	
	private static String tokenFromUri(String fullToken) {
		String result = null;
		String[] pieces = fullToken.split(SEPARATOR_TOKEN);
		if (pieces.length >= 1) {
			result = pieces[0];
		}
		return result;
	}
	
	private static UriBuilder paramsFromUri(String fullToken) {
		//Extract only the part after SEPARATOR_TOKEN
		String parameters = null;
		String[] tokenPieces = fullToken.split(SEPARATOR_TOKEN);
		if (tokenPieces.length >= 2) {
			parameters = tokenPieces[1];
		} else {
			return null;
		}
		
		//Extract parameters and put them in a map
		UriBuilder result = new UriBuilder();
		String[] pieces = parameters.split(SEPARATOR_PARAMS);
		if (pieces.length >= 1) {
			for (String piece : pieces) {
				String[] couple = piece.split(SEPARATOR_VALUES);
				if (couple.length >= 2) {
					result.add(couple[0], couple[1]);
				}
			}
		}
		return result;
	}
}
