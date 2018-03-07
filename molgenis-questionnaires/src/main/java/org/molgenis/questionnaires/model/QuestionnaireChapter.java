package org.molgenis.questionnaires.model;

import com.google.auto.value.AutoValue;
import org.molgenis.core.gson.AutoGson;

import javax.annotation.Nullable;
import java.util.List;

@AutoValue
@AutoGson(autoValueClass = AutoValue_QuestionnaireChapter.class)
public abstract class QuestionnaireChapter
{
	public abstract String getName();

	@Nullable
	public abstract String getLabel();

	@Nullable
	public abstract String getDescription();

	public abstract String getFieldType();

	public abstract boolean getReadOnly();

	public abstract boolean getVisible();

	public abstract boolean getNillable();

	@Nullable
	public abstract String getVisibleExpression();

	@Nullable
	public abstract String getNullableExpression();

	@Nullable
	public abstract String getValidationExpression();

	@Nullable
	public abstract List<String> getEnumOptions();

	@Nullable
	public abstract QuestionnaireReference getRefEntity();

	@Nullable
	public abstract List<CategoricalOption> getCategoricalOptions();

	public abstract List<QuestionnaireChapter> getAttributes();

	public static Builder builder()
	{
		return new AutoValue_QuestionnaireChapter.Builder();
	}

	@AutoValue.Builder
	public abstract static class Builder
	{
		public abstract Builder setName(String name);

		public abstract Builder setLabel(String label);

		public abstract Builder setDescription(String description);

		public abstract Builder setFieldType(String fieldType);

		public abstract Builder setReadOnly(boolean readOnly);

		public abstract Builder setVisible(boolean visible);

		public abstract Builder setNillable(boolean nillable);

		public abstract Builder setVisibleExpression(String visibleExpression);

		public abstract Builder setNullableExpression(String nullableExpression);

		public abstract Builder setValidationExpression(String validationExpression);

		public abstract Builder setEnumOptions(List<String> enumOptions);

		public abstract Builder setRefEntity(QuestionnaireReference questionnaireReference);

		public abstract Builder setCategoricalOptions(List<CategoricalOption> categoricalOptions);

		public abstract Builder setAttributes(List<QuestionnaireChapter> attributes);

		public abstract QuestionnaireChapter build();
	}
}
