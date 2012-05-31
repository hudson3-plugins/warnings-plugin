package hudson.plugins.warnings.parser;

import static junit.framework.Assert.*;
import hudson.plugins.analysis.util.model.AnnotationContainer;
import hudson.plugins.analysis.util.model.DefaultAnnotationContainer;
import hudson.plugins.analysis.util.model.FileAnnotation;
import hudson.plugins.analysis.util.model.Priority;

import java.io.IOException;
import java.util.Collection;

import org.junit.Test;

/**
 * Tests the class {@link MavenConsoleParser}.
 *
 * @author Ulli Hafner
 */
public class MavenConsoleParserTest extends ParserTester {
    /**
     * Verifies that errors and warnings are correctly picked up.
     *
     * @throws IOException
     *             if the file could not be read
     */
    @Test
    public void testParsing() throws IOException {
        Collection<FileAnnotation> warnings = new MavenConsoleParser().parse(openFile());

        assertEquals("Wrong number of warnings detected.", 4, warnings.size());
        AnnotationContainer result = new DefaultAnnotationContainer(warnings);
        assertEquals("Wrong number of errors detected.", 2, result.getNumberOfAnnotations(Priority.HIGH));
        assertEquals("Wrong number of warnings detected.", 2, result.getNumberOfAnnotations(Priority.NORMAL));
    }

    @Override
    protected String getWarningsFile() {
        return "maven-console.txt";
    }
}

