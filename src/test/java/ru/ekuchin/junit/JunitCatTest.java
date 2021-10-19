package ru.ekuchin.junit;

import org.junit.*;

public class JunitCatTest {

    private final String DEFAULT_NAME = "Мурзик";
    private final String DEFAULT_BREED = "Манул";
    private final String EMPTY_BREED = "Беспородный";

    @BeforeClass
    public static void BeforeAllTesting(){
        System.out.println("Тестирование начинается.");
    }

    @AfterClass
    public static void AfterAllTesting(){
        System.out.println("Тестирование завершено.");
    }

    @Before
    public void BeforeEveryTest(){
        System.out.println("Тест стартует.");
    }

    @After
    public void AfterEveryTest(){
        System.out.println("Тест завершился.");
    }

    @Test
    public void CreateCorrectCatWith4Params() throws IncorrectCatWeightException, IncorrectCatNameException {
        JunitCat cat = new JunitCat(DEFAULT_NAME, DEFAULT_BREED, 10, true);

        Assert.assertEquals(cat.getName(),DEFAULT_NAME);
        Assert.assertEquals(cat.getBreed(),DEFAULT_BREED);
        Assert.assertEquals(cat.getWeight(),10);
        Assert.assertTrue(cat.isAngry());
    }

    @Test
    public void CreateCorrectCatWith2Params() throws IncorrectCatWeightException, IncorrectCatNameException {
        JunitCat cat = new JunitCat(DEFAULT_NAME, 10);

        Assert.assertEquals(cat.getName(),DEFAULT_NAME);
        Assert.assertEquals(cat.getBreed(),EMPTY_BREED);
        Assert.assertEquals(cat.getWeight(),10);
        Assert.assertFalse(cat.isAngry());
    }

    @Test(expected = IncorrectCatNameException.class)
    public void UnnamedCat() throws IncorrectCatWeightException, IncorrectCatNameException {
        JunitCat cat = new JunitCat("", 10);
    }

}
