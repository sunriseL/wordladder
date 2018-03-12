package test.; 
import Main;
import org.junit.Assert;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* Main Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 12, 2018</pre> 
* @version 1.0 
*/ 
public class MainTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: is_sml(String s1, String s2) 
* 
*/ 
@Test
public void testIs_sml() throws Exception { 
    boolean result = Main.is_sml("abc","abd");
    Assert.assertEquals(true,result);
} 

/** 
* 
* Method: clear() 
* 
*/ 
@Test
public void testClear() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: main(String[] args) 
* 
*/ 
@Test
public void testMain() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: push(String s) 
* 
*/ 
@Test
public void testPushS() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: build(int len) 
* 
*/ 
@Test
public void testBuildLen() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: build(TreeSpot ptr) 
* 
*/ 
@Test
public void testBuildPtr() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: find(String s) 
* 
*/ 
@Test
public void testFind() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: push(TreeSpot spot) 
* 
*/ 
@Test
public void testPushSpot() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: pop() 
* 
*/ 
@Test
public void testPop() throws Exception { 
//TODO: Test goes here... 
} 


} 
