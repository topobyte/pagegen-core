package de.topobyte.pagegen.core;

import java.nio.file.Path;

public interface LinkResolver
{

	public String getLinkFromBase(String path);

	public String getLinkFromBase(Path path);

}
