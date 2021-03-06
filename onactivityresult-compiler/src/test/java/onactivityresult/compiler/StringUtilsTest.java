package onactivityresult.compiler;

import com.pushtorefresh.private_constructor_checker.PrivateConstructorChecker;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@SuppressWarnings("checkstyle:magicnumber")
public class StringUtilsTest {
    @Test
    public void testConstructorShouldBePrivate() {
        PrivateConstructorChecker.forClass(StringUtils.class).expectedTypeOfException(AssertionError.class).expectedExceptionMessage("No instances.").check();
    }

    @Test
    public void testGetList() {
        assertEquals("", StringUtils.getList(","));
        // noinspection NullArgumentToVariableArgMethod
        assertEquals("", StringUtils.getList(",", null));
        // noinspection RedundantArrayCreation
        assertEquals("", StringUtils.getList(",", new Object[0]));

        assertEquals("3,4,5,6", StringUtils.getList(",", 3, 4, 5, 6));
    }

    @Test
    public void testGetReadableParameters() {
        assertEquals("", StringUtils.getReadableParameters(null));
        assertEquals("", StringUtils.getReadableParameters(Collections.<VariableElement>emptyList()));
        assertEquals("Intent, Float", StringUtils.getReadableParameters(Arrays.asList(this.createVariableElement("android.content.Intent"), this.createVariableElement("java.lang.Float"))));
    }

    private VariableElement createVariableElement(final String name) {
        final VariableElement variableElement = mock(VariableElement.class);
        final TypeMirror typeMirror = mock(TypeMirror.class);
        // noinspection ResultOfMethodCallIgnored
        doReturn(name).when(typeMirror).toString();
        doReturn(typeMirror).when(variableElement).asType();

        return variableElement;
    }
}
