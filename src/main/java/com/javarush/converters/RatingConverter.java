package com.javarush.converters;

import com.javarush.constants.Rating;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, String>
{

	@Override
	public String convertToDatabaseColumn(Rating rating)
	{
		return rating.getString();
	}

	@Override
	public Rating convertToEntityAttribute(String s)
	{
		return Rating.getRating(s);
	}
}
