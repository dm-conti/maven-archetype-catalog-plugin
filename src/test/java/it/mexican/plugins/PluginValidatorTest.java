package it.mexican.plugins;

import static org.junit.Assert.*;
import it.mexican.plugin.validator.PluginValidator;

import org.junit.Test;

public class PluginValidatorTest {

	@Test(expected=AssertionError.class)
	public void test() {
		PluginValidator.validatePackage("jar");
	}

}
