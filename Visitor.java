import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Stack;

public class Visitor extends lab5BaseVisitor<Void> {
    public PrintStream ps = new PrintStream(new FileOutputStream(Test.outputPath));
    public static String exp = "";
    public static ArrayList<Variable> variableList = new ArrayList<>();
    public static int num = 0;
    public static int ifNum = 0;
    public static boolean endFlag = false;
    public static boolean funcFlag = false;
    public static boolean globalVarFlag = false;
    public static Stack<Integer> blockNumStack = new Stack<>();
    public static int blockNum = -1;

    public Visitor() throws FileNotFoundException {
        System.setOut(ps);
    }

    @Override
    public Void visitCompUnit(lab5Parser.CompUnitContext ctx) {
        blockNumStack.push(++blockNum);
        for (lab5Parser.DeclContext e : ctx.decl()) {
            globalVarFlag = true;
            visit(e);
            globalVarFlag = false;
        }
        visit(ctx.funcDef(0));
        return null;
    }

    @Override
    public Void visitFuncDef(lab5Parser.FuncDefContext ctx) {
        System.out.println("declare i32 @getint()");
        System.out.println("declare void @putint(i32)");
        System.out.println("declare i32 @getch()");
        System.out.println("declare void @putch(i32)");
        System.out.println("define dso_local i32 @main() {");
        visit(ctx.block());
        System.out.println("}");
        blockNumStack.pop();
        return null;
    }

    @Override
    public Void visitFuncType(lab5Parser.FuncTypeContext ctx) {
        return super.visitFuncType(ctx);
    }

    @Override
    public Void visitBlock(lab5Parser.BlockContext ctx) {
        blockNumStack.push(++blockNum);
        for (lab5Parser.BlockItemContext e : ctx.blockItem()) {
            visit(e);
        }
        blockNumStack.pop();
        return null;
    }

    @Override
    public Void visitBlockItem(lab5Parser.BlockItemContext ctx) {
        return super.visitBlockItem(ctx);
    }

    @Override
    public Void visitStmt(lab5Parser.StmtContext ctx) {
        if (ctx.lVal() != null) {
            if (Variable.isConst(ctx.lVal().getText())) {
                System.exit(1);
            }
            String var = ctx.lVal().getText();
            exp = "";
            visit(ctx.exp());
            String s = "";
            if (!funcFlag) {
                s = new PostfixExpression().func(exp);
            } else {
                s = "%" + num;
                funcFlag = false;
            }
            System.out.println("    store i32 " + s + ", i32* " + Variable.getStore(var));
        } else if (ctx.return_() != null) {
            exp = "";
            visit(ctx.exp());
            String s = "";
            if (!funcFlag) {
                s = new PostfixExpression().func(exp);
            } else {
                s = "%" + num;
                funcFlag = false;
            }
            System.out.println("    ret i32 " + s);
            endFlag = true;
        } else if (ctx.block() != null) {
            visit(ctx.block());
        } else if (ctx.cond() != null) {
            exp = "";
            visit(ctx.cond());
            String s = new PostfixExpression2().func(exp);

            ifNum++;
            System.out.println("    br i1 " + s + ",label %true" + ifNum + ", label %false" + ifNum);

            System.out.println("true" + ifNum + ":");
            int tempIfNum = ifNum;
            visit(ctx.stmt(0));
            ifNum = tempIfNum;
            if (endFlag) {
                endFlag = false;
            } else {
                System.out.println("    br label %end" + ifNum);
            }

            System.out.println("false" + ifNum + ":");
            tempIfNum = ifNum;
            if (ctx.stmt(1) != null) {
                visit(ctx.stmt(1));
            }
            ifNum = tempIfNum;
            if (endFlag) {
                endFlag = false;
            } else {
                System.out.println("    br label %end" + ifNum);
            }

            System.out.println("end" + ifNum + ":");
        } else {
            exp = "";
            visit(ctx.exp());
            String s = "";
            if (!funcFlag) {
                s = new PostfixExpression().func(exp);
            } else {
                s = "%" + num;
                funcFlag = false;
            }
        }
        return null;
    }

    @Override
    public Void visitReturn_(lab5Parser.Return_Context ctx) {
        return super.visitReturn_(ctx);
    }

    @Override
    public Void visitLVal(lab5Parser.LValContext ctx) {
        return super.visitLVal(ctx);
    }

    @Override
    public Void visitDecl(lab5Parser.DeclContext ctx) {
        return super.visitDecl(ctx);
    }

    @Override
    public Void visitConstDecl(lab5Parser.ConstDeclContext ctx) {
        return super.visitConstDecl(ctx);
    }

    @Override
    public Void visitBType(lab5Parser.BTypeContext ctx) {
        return super.visitBType(ctx);
    }

    @Override
    public Void visitConstDef(lab5Parser.ConstDefContext ctx) {
        Variable.checkRepeat(ctx.ident().getText());
        String var = ctx.ident().getText();
        int temp = 0;
        if (!globalVarFlag) {
            System.out.println("    %" + (num + 1) + " = alloca i32");
            temp = ++num;
        }
        exp = "";
        visit(ctx.constInitVal());
        String s = "";
        if (!funcFlag) {
            char[] str1 = exp.toCharArray();
            for (int i = 0; i < str1.length; i++) {
                String string = "";
                boolean flag = false;
                if (Character.isLetter(str1[i]) || str1[i] == '_') {
                    string += str1[i];
                    i++;
                    flag = true;
                    for (; i < str1.length && (Character.isDigit(str1[i]) || Character.isLetter(str1[i]) || str1[i] == '_'); i++) {
                        string += str1[i];
                    }
                }
                if (flag) {
                    Variable.checkExist(string);
                    if (!Variable.isConst(string)) {
                        System.exit(1);
                    }
                    i--;
                }
            }
            s = new PostfixExpression().func(exp);
        } else {
            s = "%" + num;
            funcFlag = false;
        }
        if (!globalVarFlag) {
            System.out.println("    store i32 " + s + ", i32* %" + temp);
            variableList.add(new Variable(var, "%" + temp, "null", 0, blockNumStack.peek(), 2, true));
        } else {
            System.out.println("@" + var + " = dso_local global i32 " + s);
            variableList.add(new Variable(var, "@" + var, "null", Integer.parseInt(s), blockNumStack.peek(), 2, true));
        }
        return null;
    }

    @Override
    public Void visitConstInitVal(lab5Parser.ConstInitValContext ctx) {
        return super.visitConstInitVal(ctx);
    }

    @Override
    public Void visitConstExp(lab5Parser.ConstExpContext ctx) {
        return super.visitConstExp(ctx);
    }

    @Override
    public Void visitVarDecl(lab5Parser.VarDeclContext ctx) {
        return super.visitVarDecl(ctx);
    }

    @Override
    public Void visitVarDef(lab5Parser.VarDefContext ctx) {
        Variable.checkRepeat(ctx.ident().getText());
        if (ctx.initVal() == null) {
            String var = ctx.ident().getText();
            int temp = 0;
            if (!globalVarFlag) {
                System.out.println("    %" + (num + 1) + " = alloca i32");
                temp = ++num;
                variableList.add(new Variable(var, "%" + temp, "null", 0, blockNumStack.peek(), 1, false));
            } else {
                System.out.println("@" + var + " = dso_local global i32 0");
                variableList.add(new Variable(var, "@" + var, "null", 0, blockNumStack.peek(), 1, false));
            }
        } else {
            String var = ctx.ident().getText();
            int temp = 0;
            if (!globalVarFlag) {
                System.out.println("    %" + (num + 1) + " = alloca i32");
                temp = ++num;
            }
            exp = "";
            visit(ctx.initVal());
            String s = "";
            if (!funcFlag) {
                s = new PostfixExpression().func(exp);
            } else {
                s = "%" + num;
                funcFlag = false;
            }
            if (!globalVarFlag) {
                System.out.println("    store i32 " + s + ", i32* %" + temp);
                variableList.add(new Variable(var, "%" + temp, "null", 0, blockNumStack.peek(), 2, false));
            } else {
                System.out.println("@" + var + " = dso_local global i32 " + s);
                variableList.add(new Variable(var, "@" + var, "null", 0, blockNumStack.peek(), 2, false));
            }
        }
        return null;
    }

    @Override
    public Void visitInitVal(lab5Parser.InitValContext ctx) {
        return super.visitInitVal(ctx);
    }

    @Override
    public Void visitCond(lab5Parser.CondContext ctx) {
        return super.visitCond(ctx);
    }

    @Override
    public Void visitExp(lab5Parser.ExpContext ctx) {
        return super.visitExp(ctx);
    }

    @Override
    public Void visitLOrExp(lab5Parser.LOrExpContext ctx) {
        if (ctx.lOrExp() != null) {
            visit(ctx.lOrExp());
            exp += "|";
            visit(ctx.lAndExp());
        } else {
            visit(ctx.lAndExp());
        }
        return null;
    }

    @Override
    public Void visitLAndExp(lab5Parser.LAndExpContext ctx) {
        if (ctx.lAndExp() != null) {
            visit(ctx.lAndExp());
            exp += "&";
            visit(ctx.eqExp());
        } else {
            visit(ctx.eqExp());
        }
        return null;
    }

    @Override
    public Void visitEqExp(lab5Parser.EqExpContext ctx) {
        return super.visitEqExp(ctx);
    }

    @Override
    public Void visitEqNeq(lab5Parser.EqNeqContext ctx) {
        if (ctx.getText().equals("==")) {
            exp += "="; // ==
        } else {
            exp += "~"; // !=
        }
        return null;
    }

    @Override
    public Void visitRelExp(lab5Parser.RelExpContext ctx) {
        return super.visitRelExp(ctx);
    }

    @Override
    public Void visitCompare(lab5Parser.CompareContext ctx) {
        if (ctx.getText().equals("<=")) {
            exp += "《"; // <=
        } else if (ctx.getText().equals(">=")) {
            exp += "》"; // >=
        } else {
            exp += ctx.getText(); // < >
        }
        return null;
    }

    @Override
    public Void visitAddExp(lab5Parser.AddExpContext ctx) {
        return super.visitAddExp(ctx);
    }

    @Override
    public Void visitAddSub(lab5Parser.AddSubContext ctx) {
        exp += ctx.getText();
        return null;
    }

    @Override
    public Void visitMulExp(lab5Parser.MulExpContext ctx) {
        return super.visitMulExp(ctx);
    }

    @Override
    public Void visitMulDiv(lab5Parser.MulDivContext ctx) {
        exp += ctx.getText();
        return null;
    }

    @Override
    public Void visitUnaryExp(lab5Parser.UnaryExpContext ctx) {
        if (ctx.primaryExp() != null) {
            visit(ctx.primaryExp());
        } else if (ctx.ident() != null) {
            String func = ctx.ident().getText();
            if (func.equals("getint") || func.equals("getch") || func.equals("putint") || func.equals("putch")) {
                if (func.equals("getint") && ctx.funcRParams() == null) {
                    System.out.println("    %" + (num + 1) + " = call i32 @getint()");
                    num++;
                    funcFlag = true;
                } else if (func.equals("putint") && ctx.funcRParams() != null) {
                    exp = "";
                    visit(ctx.funcRParams());
                    String s = new PostfixExpression().func(exp);
                    System.out.println("    call void @putint(i32 " + s + ")");
                    funcFlag = true;
                } else if (func.equals("getch") && ctx.funcRParams() == null) {
                    System.out.println("    %" + (num + 1) + " = call i32 @getch()");
                    num++;
                    funcFlag = true;
                } else if (func.equals("putch") && ctx.funcRParams() != null) {
                    exp = "";
                    visit(ctx.funcRParams());
                    String s = new PostfixExpression().func(exp);
                    System.out.println("    call void @putch(i32 " + s + ")");
                    funcFlag = true;
                } else {
                    System.exit(1);
                }
            } else {
                System.exit(1);
            }
        } else {
            visit(ctx.unaryOp());
            visit(ctx.unaryExp());
        }
        return null;
    }

    @Override
    public Void visitFuncRParams(lab5Parser.FuncRParamsContext ctx) {
        return super.visitFuncRParams(ctx);
    }

    @Override
    public Void visitPrimaryExp(lab5Parser.PrimaryExpContext ctx) {
        if (ctx.exp() != null) {
            exp += "(";
            visit(ctx.exp());
            exp += ")";
        } else if (ctx.lVal() != null) {
            exp += ctx.lVal().getText();
        } else {
            visit(ctx.number());
        }
        return null;
    }

    @Override
    public Void visitUnaryOp(lab5Parser.UnaryOpContext ctx) {
        exp += ctx.getText();
        return null;
    }

    @Override
    public Void visitIdent(lab5Parser.IdentContext ctx) {
        return super.visitIdent(ctx);
    }

    @Override
    public Void visitNumber(lab5Parser.NumberContext ctx) {
        if (ctx.DecimalConst() != null) {
            exp += ctx.DecimalConst().getText();
        } else if (ctx.OctalConst() != null) {
            if (ctx.OctalConst().getText().equals("0")) {
                exp += "0";
            } else {
                String s = ctx.OctalConst().getText().substring(1);
                exp += String.valueOf(Integer.parseInt(s, 8));
            }
        } else {
            String s = ctx.HexadecimalConst().getText().substring(2);
            exp += String.valueOf(Integer.parseInt(s, 16));
        }
        return null;
    }
}
