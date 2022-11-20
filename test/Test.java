import org.junit.After;
import org.junit.Before;

public class Test {
    @Before
    public void test1(){
        System.out.println("前置条件");
    }




    public static void main(String[] args) {
        System.out.println("测试类启动成功！");
    }


    @After
    public void test2(){
        System.out.println("后置条件");
    }

}
