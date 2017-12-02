package com.apkfuns.compiler;

import com.apkfuns.annotations.BindClass;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;


/**
 * javapoet 语法 https://github.com/square/javapoet
 */
@AutoService(Processor.class)
public class XProcesser extends AbstractProcessor {

    // 可以在 Gradle Console 查看日志
    private Messager messager;
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        messager = processingEnvironment.getMessager();
        filer = processingEnvironment.getFiler();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(BindClass.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        for (Element element : roundEnvironment.getElementsAnnotatedWith(BindClass.class)) {
            messager.printMessage(Diagnostic.Kind.NOTE, "#" + element);
            // 获取注解的字段内容
            BindClass annotation = element.getAnnotation(BindClass.class);
            messager.printMessage(Diagnostic.Kind.NOTE, "@" + annotation.name());

            // 动态创建 Java 类
            ClassName contextCls = ClassName.get("android.content", "Context");
            ClassName toastCls = ClassName.get("android.widget", "Toast");
            MethodSpec methodSpec = MethodSpec.methodBuilder("showToast")
                    .addModifiers(Modifier.STATIC, Modifier.PUBLIC)
                    .addParameter(contextCls, "context")
                    .addParameter(String.class, "msg")
                    .addStatement("$T.makeText(context, msg, Toast.LENGTH_SHORT).show()", toastCls)
                    .build();
            TypeSpec typeSpec = TypeSpec.classBuilder("Utils")
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                    .addMethod(methodSpec)
                    .build();
            JavaFile javaFile = JavaFile.builder("com.apkfuns.annotationprocessordemo.utils", typeSpec)
                    .build();
            try {
                javaFile.writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 反射获取属性和方法
            if (element instanceof TypeElement) {
                for (Element childElement : element.getEnclosedElements()) {
                    if (childElement instanceof ExecutableElement) {
                        // 方法 <包括构造函数>
                        ExecutableElement method = (ExecutableElement) childElement;
                        messager.printMessage(Diagnostic.Kind.NOTE, "%" + method.getSimpleName().toString());
                        messager.printMessage(Diagnostic.Kind.NOTE, method.getSimpleName().getClass().toString());
                    } else if (childElement instanceof VariableElement) {
                        // 属性
                        VariableElement variable = (VariableElement) childElement;
                        messager.printMessage(Diagnostic.Kind.NOTE, variable.getSimpleName());
                    }
                }
            }
        }
        return false;
    }

    /**
     * 在桌面上生成 Java 类 (~/Hello/com/apkfuns/annotaion/HelloWorld.java)
     * 需要 rebuild
     *
     * @throws IOException
     */
    private void helloWorldGenerator() throws IOException {
        MethodSpec main = MethodSpec.methodBuilder("main").addModifiers(Modifier.PUBLIC, Modifier.STATIC).returns(void.class).addParameter(String[].class, "args").addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!").build();
        MethodSpec testMethod = MethodSpec.methodBuilder("test")
                .addModifiers(Modifier.PRIVATE)
                .returns(String.class)
                .addJavadoc("java doc")
                .addParameter(int.class, "abc")
                .addComment("拉拉拉拉")
                .addException(IOException.class)
                .addAnnotation(Deprecated.class)
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                .addStatement("return null")
                .build();
        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld").addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main).addMethod(testMethod)
                .addField(FieldSpec.builder(Object.class, "mLock", Modifier.PRIVATE).initializer("new Object()").build())
                .build();
        JavaFile javaFile = JavaFile.builder("com.apkfuns.annotation", helloWorld).build();
        javaFile.writeTo(new File(System.getProperty("user.home") + "/Desktop/Hello"));
    }
}
