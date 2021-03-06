package onactivityresult;

import com.google.common.base.Joiner;
import com.google.testing.compile.JavaFileObjects;

import org.junit.Test;

import javax.tools.JavaFileObject;

import static onactivityresult.util.JavaFileObjectUtils.assertEquals;

@SuppressWarnings({ "checkstyle:magicnumber", "PMD.AvoidDuplicateLiterals" })
public class OnActivityResultInheritanceTest {
    @Test
    public void testOnActivityResult() {
        //@formatter:off
        final JavaFileObject actualSource = JavaFileObjects.forSourceString("test/BaseActivity", Joiner.on('\n').join(
                "package test;",

                "import onactivityresult.OnActivityResult;",

                "public class BaseActivity {",
                    "@OnActivityResult(requestCode = 3) public void test() {}",
                "}",

                "class TestActivity extends BaseActivity {",
                    "@OnActivityResult(requestCode = 3) public void foo() {}",
                    "@OnActivityResult(requestCode = 4) public void bar() {}",
                "}",

                "class AnotherTestActivity extends TestActivity {",
                    "@OnActivityResult(requestCode = 4) public void bar() {}",
                    "@OnActivityResult(requestCode = 5) public void abc() {}",
                "}"
                )
        );
        //@formatter:on

        //@formatter:off
        final JavaFileObject expectedSourceBase = JavaFileObjects.forSourceString("test/BaseActivity$$OnActivityResult", Joiner.on('\n').join(
                "// Generated code from OnActivityResult. Do not modify!",
                "package test;",

                "import android.content.Intent;",
                "import java.lang.Override;",
                "import onactivityresult.internal.IOnActivityResult;",
               
                "public class BaseActivity$$OnActivityResult<T extends BaseActivity> implements IOnActivityResult<T> {",
                    "@Override",
                    "public void onResult(final T t, final int requestCode, final int resultCode, final Intent intent) {",
                        "if (requestCode == 3) {",
                            "t.test();",
                        "}",
                    "}",
                "}")
        );
        //@formatter:on

        //@formatter:off
        final JavaFileObject expectedSourceTest = JavaFileObjects.forSourceString("test/TestActivity$$OnActivityResult", Joiner.on('\n').join(
                "// Generated code from OnActivityResult. Do not modify!",
                "package test;",

                "import android.content.Intent;",
                "import java.lang.Override;",

                "public class TestActivity$$OnActivityResult<T extends TestActivity> extends BaseActivity$$OnActivityResult<T> {",
                    "@Override",
                    "public void onResult(final T t, final int requestCode, final int resultCode, final Intent intent) {",
                        "super.onResult(t, requestCode, resultCode, intent);",

                        "if (requestCode == 3) {",
                            "t.foo();",
                        "} else if (requestCode == 4) {",
                            "t.bar();",
                        "}",
                    "}",
                "}")
        );
        //@formatter:on

        //@formatter:off
        final JavaFileObject expectedSourceAnotherTest = JavaFileObjects.forSourceString("test/AnotherTestActivity$$OnActivityResult", Joiner.on('\n').join(
                "// Generated code from OnActivityResult. Do not modify!",
                "package test;",

                "import android.content.Intent;",
                "import java.lang.Override;",

                "public class AnotherTestActivity$$OnActivityResult<T extends AnotherTestActivity> extends TestActivity$$OnActivityResult<T> {",
                    "@Override",
                    "public void onResult(final T t, final int requestCode, final int resultCode, final Intent intent) {",
                        "super.onResult(t, requestCode, resultCode, intent);",

                        "if (requestCode == 4) {",
                            "t.bar();",
                        "} else if (requestCode == 5) {",
                            "t.abc();",
                        "}",
                    "}",
                "}")
        );
        //@formatter:on

        assertEquals(actualSource, expectedSourceBase, expectedSourceTest, expectedSourceAnotherTest);
    }
}
