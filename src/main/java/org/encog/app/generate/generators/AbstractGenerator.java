/*
 * Encog(tm) Core v3.2 - Java Version
 * http://www.heatonresearch.com/encog/
 * http://code.google.com/p/encog-java/
 
 * Copyright 2008-2012 Heaton Research, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *   
 * For more information on Heaton Research copyrights, licenses 
 * and trademarks visit:
 * http://www.heatonresearch.com/copyright
 */
package org.encog.app.generate.generators;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.TreeSet;

public abstract class AbstractGenerator implements ProgramGenerator {
	public static final int INDENT_SPACES = 4;

	private final StringBuilder contents = new StringBuilder();
	private int currentIndent = 0;
	private final Set<String> includes = new TreeSet<String>();

	public void addBreak() {
		this.contents.append("\n");
	}

	public void addInclude(final String str) {
		this.includes.add(str);
	}

	public void addLine(final String line) {
		for (int i = 0; i < this.currentIndent; i++) {
			this.contents.append(' ');
		}
		this.contents.append(line);
		this.contents.append("\n");
	}

	public void addToBeginning(final String str) {
		this.contents.insert(0, str);
	}

	@Override
	public String getContents() {
		return this.contents.toString();
	}

	public Set<String> getIncludes() {
		return this.includes;
	}

	public void indentLine(final String line) {
		addLine(line);
		this.currentIndent += AbstractGenerator.INDENT_SPACES;
	}

	public void unIndentLine(final String line) {
		this.currentIndent -= AbstractGenerator.INDENT_SPACES;
		addLine(line);
	}

	@Override
	public void writeContents(final File targetFile) {
		try {
			final FileWriter outFile = new FileWriter(targetFile);
			final PrintWriter out = new PrintWriter(outFile);
			out.print(this.contents.toString());
			out.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

}