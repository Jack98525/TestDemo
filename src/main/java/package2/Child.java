package package2;

import org.example.package1.Parent;

public class Child extends Parent {
    public void accessParentMembers() {
        // 可以访问 public 成员
        System.out.println(publicField); // 输出: Public Field
        publicMethod(); // 输出: Public Method

        // 可以访问 protected 成员
        System.out.println(protectedField); // 输出: Protected Field
        protectedMethod(); // 输出: Protected Method

        // 无法访问默认成员（包级访问）
        // System.out.println(defaultField); // 编译错误
        // defaultMethod(); // 编译错误

        // 无法访问 private 成员
        // System.out.println(privateField); // 编译错误
        // privateMethod(); // 编译错误

    }
}