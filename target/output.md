# <font color=LightBlue>目录</font>

- <a href=#715753497>外部命令</a>
- <a href=#900237984>特殊字符</a>
- <a href=#646676535>光标跳转</a>
- <a href=#728782474>对比文件</a>
- <a href=#1125457273>简易模式Vim</a>
- <a href=#15038039>打开Vim后运行指定命令</a>
- <a href=#-1463670626>获取命令返回值直接带入Vim</a>
- <a href=#653784>书签</a>
- <a href=#773248134>折叠功能</a>

------

## <a id="715753497">外部命令</a>

命令模式下（冒号：）

输入 ！+ 命令 相当于运行该命令

输入 .! + 命令 相当于将命令的输出值带回Vim

▲ figlet会将输入的字母转为字符画

## <a id="900237984">特殊字符</a>

输入<a id="900237984">特殊字符</a>

**Insert模式**下使用

| 快捷键    | 对应应用          |
|:------:|:------------- |
| Ctrl+v | AscIi码        |
| Ctrl+k | 双生字符（比如*1表示⭐） |

## <a id="646676535">光标跳转</a>

- 跳转至对应行
  
  ```bash
  vim +行数（有一个加号！）文件名  
  ```

- 跳转至对应内容位置
  
  ```bash
  vim +/正则 文件名
  ```

<img title="" src="file:///C:/Users/28332/AppData/Roaming/marktext/images/2022-03-30-23-01-01-image.png" alt="" width="296">

## <a id="728782474">对比文件</a>

- 外部打开
  
  为vim指令添加-d选项，跟两个文件名进入<mark>diff模式</mark>，打开两个缓冲区。

```bash
vim -d file1 file2
```

- 内部对比
  
  先垂直分割屏幕，再对屏幕中的所有缓冲区执行differthis。 

```mermaid
graph TD
A[打开Vim] --> B[打开命令模式] 
C[当前屏幕所有窗口进入diff模式]
D[退出diff模式]
E[垂直/水平切割屏幕]
B --windo this-->C
B --diffoff--> D
B --vs/hs File_name-->E
```

## <a id="1125457273">简易模式Vim</a>

```mermaid
flowchart TD
A([vim -y])
B[Vim Easy Mode]
D[简易模式下只有插入功能]
B-.->D
A-->B
C[离开Vim]
B--Ctrl+O进入冒号命令模式-->C
```

## <a id="15038039">打开Vim后运行指定命令</a>

```bash
vim -c "命令" File
```

or

```bash
vim "+ 命令" File
```

![](C:\Users\28332\AppData\Roaming\marktext\images\2022-03-31-08-20-37-image.png)

## <a id="-1463670626">获取命令返回值直接带入Vim</a>

```bash
<command> | vim -
```

“-” 符号表示从标准输出（屏幕打印）中读取

## <a id="653784">书签</a>

```mermaid
graph TD
A([正常模式<br/> NORMAL Mode])
B[m+ a-z or A-Z <br/> 表示创建标签]
C["标签跳转至<a id="653784">书签</a>所在行头部: '(正引号)标签"]
D[删除<a id="653784">书签</a>:dm+ 待删除标签]
E["'['' or ']'+正反引号分别表示上一个和下一个<a id="653784">书签</a>"]
F["标签跳转至<a id="653784">书签</a>所在行列: `(反引号，esc下面那个)标签"]
A --相关命令--> B
subgraph -
    direction LR
    B ==> C
    C -.-> D
    C -.-> E
    E -.-> F

end
```

### [Using marks | Vim Tips Wiki](https://vim.fandom.com/wiki/Using_marks)

## <a id="773248134">折叠功能</a>

```mermaid
graph TD
A([命令模式<br/>Command Mode])
B["查看当前Foldmethod<br/>set foldmethod"]
C["切换当前Foldmethod<br/>set foldmethod=# method #"]
subgraph 查看与切换命令
A-->B-->C
end
```

### Method

- `manual` – folds must be defined by entering commands (such as [zf](http://vimdoc.sourceforge.net/cgi-bin/help?tag=zf))

- `indent` – groups of lines with the same indent form a fold

- `syntax` – folds are defined by syntax highlighting

- `expr` – folds are defined by a user-defined expression

- `marker` – special characters can be manually or automatically added to your text to flag the start and end of folds

- `diff` – used to fold unchanged text when viewing differences (automatically set in diff mode)

```mermaid
graph TD
a[较好用的Method]
b{{indent:根据行缩进折叠}}
c{{marker:根据特殊符号折叠}}
h{{manual:手动选中键入zf}}
j[zM:全部折叠]
k[zR:全部展开]
a-->b
a-->c
a-->h
h-->e
b-->e
c-->e
subgraph .
direction TB
e([正常模式<br/>Normal Mode])
d["za:改变折叠状态<br/>(关变开，开变关)"]
e-.->d
d-.->f[zo:开]
f-.->j
d-.->g[zc:关]
g-.->k
end
```

### [Folding | Vim Tips Wiki ](https://vim.fandom.com/wiki/Folding)

> #### 组合技：vim "+ : set foldmethod=marker" hadoop-env.sh

打开时自动将文本的foldmethod调为marker

 <font color=Red>:imp:Error</font>

<font color=Red>还是尽量不要使用<a id="773248134">折叠功能</a>的marker，会导致部分的字符Error！</font>
