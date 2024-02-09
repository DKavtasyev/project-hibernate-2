package com.javarush.constants;

public enum Rating
{
	G("G"),
	PG("PG"),
	PG13("PG-13"),
	R("R"),
	NC17("NC-17");

	private final String rating;

	Rating(String rating)
	{
		this.rating = rating;
	}

	public String getString()
	{
		return rating;
	}

	public static Rating getRating(String rating) throws IllegalArgumentException
	{
		Rating[] values = Rating.values();
		for (Rating r : values)
		{
			if (r.getString().equals(rating))
				return r;
		}
		throw new IllegalArgumentException("Code \"" + rating + "\" doesn't exist!");
	}
}
