#include <stddef.h>

#define TRUE 1
#define FALSE 0
#define LH 1
#define EH 0
#define RH -1

typedef int Status;

typedef struct BitNode {
    char* data;
    int bf;
    struct BitNode *lchild, *rchild;
} BiTNode, *BiTree;


Status string_contain(const BiTree T, const char* key);

void in_order_traverse(BiTree T, const char** suggestions, int* index, char* key, size_t max_suggestions);

Status search_bst(BiTree T, char* key, BiTree f, BiTree *p);

void R_Rotate(BiTree *p);

void L_Rotate(BiTree *p);

void LeftBalance(BiTree *T);

void RightBalance(BiTree *T);

Status InsertAVL(BiTree *T,char* e,Status *taller);
