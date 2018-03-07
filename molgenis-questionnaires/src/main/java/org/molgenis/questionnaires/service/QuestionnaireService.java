package org.molgenis.questionnaires.service;

import org.molgenis.questionnaires.response.QuestionnaireListItemResponse;
import org.molgenis.questionnaires.response.QuestionnaireResponse;

import java.util.List;

public interface QuestionnaireService
{
	/**
	 * Return a list of all questionnaires
	 * Status is set to open if a questionnaire already has an entry for the current user.
	 *
	 * @return A List of {@link QuestionnaireListItemResponse}
	 */
	List<QuestionnaireListItemResponse> getQuestionnaires();

	/**
	 * Retrieves a questionnaire row for the current user based on questionnaire ID.
	 * If no row exists for the current user, a row is created.
	 *
	 * @param id The ID of a questionnaire
	 */
	QuestionnaireResponse getQuestionnaire(String id);

	/**
	 * Retrieve static content for a specific questionnaire containing a "Thank you" text which is shown
	 * on submission.
	 * <p>
	 * If no static content is specified, will return a text with HTML contents by default.
	 *
	 * @param id The ID of a questionnaire
	 * @return A piece of text which can be shown after submission of a questionnaire
	 */
	String getQuestionnaireSubmissionText(String id);
}
