package org.molgenis.questionnaires.service;

import org.molgenis.questionnaires.request.QuestionnaireCreateRequest;
import org.molgenis.questionnaires.response.QuestionnaireResponse;

import java.util.List;

public interface QuestionnaireService
{
	/**
	 * Creates a questionnaire and an additional questionnaire data table
	 *
	 * @param request A request containing questionnaire name and SurveyJS JSON schema
	 */
	void createQuestionnaire(QuestionnaireCreateRequest request);

	/**
	 * Return a list of all questionnaires
	 * Creates a questionnaire entry for the current user if it does not yet exist
	 *
	 * @return A List of {@link QuestionnaireResponse}
	 */
	List<QuestionnaireResponse> getQuestionnaires();

	/**
	 * Retrieve a Questionnaire by id
	 * Sets the questionnaire status to 'OPEN' when it is retrieved for the first time
	 *
	 * @param id The ID of a questionnaire
	 * @return A {@link QuestionnaireResponse}
	 */
	QuestionnaireResponse getQuestionnaire(String id);
}
