package core.daylife.service.elements;

import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractGenerator;

public abstract class AbstractJavaServiceMethodGenerator extends AbstractGenerator {
	public abstract void addInterfaceElements(TopLevelClass interfaze);
}
