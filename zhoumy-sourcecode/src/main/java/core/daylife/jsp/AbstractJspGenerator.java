package core.daylife.jsp;

import java.util.List;

import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.codegen.AbstractGenerator;

public abstract class AbstractJspGenerator extends AbstractGenerator {

	public String dirName;

	public String fileName;

	public abstract List<String> getCompilationUnits();
}
