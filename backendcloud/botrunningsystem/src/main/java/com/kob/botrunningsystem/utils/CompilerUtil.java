package com.kob.botrunningsystem.utils;

import com.itranswarp.compiler.JavaStringCompiler;

import java.util.Map;

public class CompilerUtil {
    public static BotInterface generateClass(String className, String packageName, String javaCode) throws Exception {
        // 声明包名
        String prefix = String.format("package %s;", packageName);
        // 全类名
        String fullName = String.format("%s.%s", packageName, className);
        // 编译器
        JavaStringCompiler compiler = new JavaStringCompiler();
        // 编译：compiler.compile("Main.java", source)
        Map<String, byte[]> results = compiler.compile(className + ".java", prefix + javaCode);
        // 加载内存中byte到Class<?>对象
        Class<?> clazz = compiler.loadClass(fullName, results);
        // 创建实例
        BotInterface instance = (BotInterface)clazz.getDeclaredConstructor().newInstance();

        return instance;
    }
}
