package de.topobyte.pagegen.core;

import de.topobyte.webpaths.WebPath;

public interface BaseFileGeneratorFactory
{

	public BaseFileGenerator create(WebPath path);

}
