package com.javarush.converters;

import com.javarush.constants.SpecialFeatures;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class SpecialFeaturesConverter implements AttributeConverter<SpecialFeatures[], String>
{

	@Override
	public String convertToDatabaseColumn(SpecialFeatures[] specialFeatures)
	{
		return Arrays.stream(specialFeatures).map(SpecialFeatures::getValue).collect(Collectors.joining(","));
	}

	@Override
	public SpecialFeatures[] convertToEntityAttribute(String s)
	{
		String[] values = s.split(",");
		List<SpecialFeatures> list = Arrays.stream(values).map(SpecialFeatures::getFeatureByValue).toList();
		SpecialFeatures[] array = new SpecialFeatures[list.size()];
		return list.toArray(array);
	}
}
