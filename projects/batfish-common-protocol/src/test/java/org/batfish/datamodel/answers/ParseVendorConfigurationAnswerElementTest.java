package org.batfish.datamodel.answers;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.SortedMap;
import java.util.TreeMap;
import org.batfish.common.BatfishException;
import org.junit.Before;
import org.junit.Test;

/** Tests for {@link ParseVendorConfigurationAnswerElement} */
public class ParseVendorConfigurationAnswerElementTest {

  ParseVendorConfigurationAnswerElement _element;

  @Test
  public void checkEmptyErrors() {
    assertThat(_element.getErrors().size(), is(0));
  }

  @Test
  public void checkNonEmptyErrors() {
    BatfishException exception = new BatfishException("sample exception");
    _element.getErrors().put("error", exception);
    assertThat(_element.getErrors().size(), is(1));
  }

  @Before
  public void setUp() {
    _element = new ParseVendorConfigurationAnswerElement();
  }

  @Test
  public void testGetErrors() {
    BatfishException exception = new BatfishException("sample exception");
    _element.getErrors().put("error", exception);
    assertThat(_element.getErrors().get("error"), is(exception));
  }

  @Test
  public void testPrettyPrint() {
    BatfishException exception = new BatfishException("sample exception");
    _element.getErrors().put("sampleError", exception);
    StringBuilder expected = new StringBuilder();
    expected.append("Results of parsing vendor configurations\n");
    expected.append("\n  sampleError[Parser errors]\n");
    for (String line :
        _element.getErrors().get("sampleError").getBatfishStackTrace().getLineMap()) {
      expected.append("    " + line + "\n");
    }
    assertThat(_element.prettyPrint(), equalTo(expected.toString()));
  }

  @Test
  public void testSetErrors() {
    BatfishException exception = new BatfishException("sample exception");
    SortedMap<String, BatfishException> errors = new TreeMap<>();
    errors.put("error", exception);
    _element.setErrors(errors);
    assertThat(_element.getErrors().get("error"), is(exception));
  }
}
