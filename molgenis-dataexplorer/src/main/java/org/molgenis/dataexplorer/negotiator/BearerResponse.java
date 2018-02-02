package org.molgenis.dataexplorer.negotiator;

import com.google.auto.value.AutoValue;
import org.molgenis.core.gson.AutoGson;

@AutoValue
@AutoGson(autoValueClass = AutoValue_BearerResponse.class)
public abstract class BearerResponse
{
	public abstract String getAccess_token();
}