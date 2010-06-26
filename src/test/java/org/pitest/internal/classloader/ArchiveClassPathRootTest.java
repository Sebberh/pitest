/*
 * Copyright 2010 Henry Coles
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package org.pitest.internal.classloader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class ArchiveClassPathRootTest {

  private ArchiveClassPathRoot testee;

  @Before
  public void setup() throws Exception {
    this.testee = new ArchiveClassPathRoot(new File("mytests.jar"));
  }

  @Test
  public void testClassNamesReturnsAllClassNamesIArchive() {
    final Collection<String> expected = Arrays.asList(
        "injar.p1.P1NoTest$InnerTest", "injar.p1.P1NoTest", "injar.p1.P1Test",
        "injar.p2.P2Test");
    assertEquals(expected, this.testee.classNames());
  }

  @Test
  public void testGetDataReturnsNullForUnknownClass() throws Exception {
    assertNull(this.testee.getData("bar"));
  }

  @Test
  public void testGetDataReturnsInputStreamForAKnownClass() throws Exception {
    assertNotNull(this.testee.getData("injar.p1.P1Test"));
  }

  @Test
  public void testGetResourceReturnsNullForAnUnknownResource() throws Exception {
    assertNull(this.testee.getResource("bar"));
  }

  @Test
  public void testGetResourceReturnsURLForAKnownResource() throws Exception {
    assertNotNull(this.testee.getResource("injar/p1/P1Test.class"));
  }
}
