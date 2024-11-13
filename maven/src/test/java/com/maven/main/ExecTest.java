package com.maven.main;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ExecTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ExecTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ExecTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    //正常系試験01
    //住所が見つかるパターン
    @org.junit.Test
    public void testExec_normal_01() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String[] args = {"5998248"};
        Exec.main(args);
        
        String expectedOutput = "[検索結果を出力します]" + System.lineSeparator() +
                                "郵便番号:5998248" + System.lineSeparator() +
                                "住所(漢字):大阪府 堺市中区 深井畑山町" + System.lineSeparator() +
                                "住所(ｶﾅ):ｵｵｻｶﾌ ｻｶｲｼﾅｶｸ ﾌｶｲﾊﾀﾔﾏﾁｮｳ" + System.lineSeparator() +
                                "都道府県コード:27" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    //正常系試験02
    //住所が存在しないパターン
    @org.junit.Test
    public void testExec_normal_02() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String[] args = {"1234567"};
        Exec.main(args);

        String expectedOutput = "[検索結果を出力します]" + System.lineSeparator() +
                                "入力した郵便番号から住所は存在しませんでした。" + System.lineSeparator() +
                                "message:null" + System.lineSeparator() +
                                "results:null" + System.lineSeparator() +
                                "status:200" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    //異常系試験01
    //引数が1つではない場合
    @org.junit.Test
    public void testExec_Abnormal_01() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String[] args = {"1234567","1234567"};
        Exec.main(args);

        String expectedOutput = "一つの引数(郵便番号のみ)設定してください。" + System.lineSeparator() +
                                "処理を終了します" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    //異常系試験02
    //引数が郵便番号(7ケタ)出ない場合
    @org.junit.Test
    public void testExec_Abnormal_02() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String[] args = {"123456789"};
        Exec.main(args);

        String expectedOutput = "郵便番号の指定が間違っています。" + System.lineSeparator() +
                                "処理を終了します" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
    
}
