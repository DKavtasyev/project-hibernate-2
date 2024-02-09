package com.javarush.constants;

public enum SpecialFeatures
{
	TRAILERS("Trailers"),
	COMMENTARIES("Commentaries"),
	DELETED_SCENES("Deleted Scenes"),
	BEHIND_THE_SCENES("Behind the Scenes");

	private final String value;

	SpecialFeatures(String value)
	{
		this.value = value;
	}

	public String getValue()
	{
		return value;
	}

	public static SpecialFeatures getFeatureByValue(String value)
	{
		if (value == null || value.isEmpty())
			return null;

		SpecialFeatures[] values = SpecialFeatures.values();
		for (SpecialFeatures feature : values)
		{
			if (feature.getValue().equals(value))
				return feature;
		}
		throw new IllegalArgumentException("Code \"" + value + "\" doesn't exist!");
	}
}
