package de.topobyte.pagegen.core;

import java.nio.file.Path;

public interface Context
{

	public Path getFavIcon();

	public Path fromBase(String relative);

	public Path fromBase(Path relative);

	public Path relative(Path file);

}
