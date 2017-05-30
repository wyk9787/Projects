#include "BBST.h"

#include <stdlib.h>
#include <stddef.h>
#include <stdio.h>
#include <string.h>

//return if T->data contains the key in the string
Status string_contain(const BiTree T, const char* key) {
    return (strlen(T->data) >= strlen(key)) && (strncmp(T->data, key, strlen(key)) == 0);
}

//do in order traverse to add appropriate suggestions to the array
void in_order_traverse(BiTree T, const char** suggestions, int* index, char* key, size_t max_suggestions) {
    if(T == NULL || *index > max_suggestions - 1) {
        return;
    } else {
        in_order_traverse(T->lchild, suggestions, index, key, max_suggestions);
        if(string_contain(T, key)){
            suggestions[*index] = T->data;
            (*index)++;
        }
        in_order_traverse(T->rchild, suggestions, index, key, max_suggestions);
    }
}

//search for the first element that contains the key in the tree
Status search_bst(BiTree T, char* key, BiTree f, BiTree *p) {
    if (!T) {
        *p = f;
        return FALSE;
    } else if (string_contain(T, key)) {
        *p = T;
        return TRUE;
    } else if (strcmp(key, T->data) < 0) {
        return search_bst(T->lchild, key, T, p);
    } else {
        return search_bst(T->rchild, key, T, p);
    }
}

//insert the key to the tree
// Status insert_bst(BiTree *T, char* key) {
//     BiTree p, s;
//     if(!search_bst(*T, key, NULL, &p)) {
//         s = (BiTree) malloc (sizeof (BiTNode));
//         s->data = key;
//         s->lchild = s->rchild = NULL;
//         if(!p) {
//             *T = s;
//         } else if (strcmp(key, p->data) < 0) {
//             p->lchild = s;
//         } else {
//             p->rchild = s;
//         }
//         return TRUE;
//     } else {
//         return FALSE;
//     }
// }

void R_Rotate(BiTree *p) {
    BiTree L;
    L = (*p)->lchild;
    (*p)->lchild = L->rchild;
    L->rchild = (*p);
    *p = L;
}

void L_Rotate(BiTree *p) {
    BiTree R;
    R = (*p)->rchild;
    (*p)->rchild = R->lchild;
    R->lchild = (*p);
    *p = R;
}

void LeftBalance(BiTree *T)
{
	BiTree L,Lr;
	L=(*T)->lchild;
	switch(L->bf) {
		 case LH:
			(*T)->bf=L->bf=EH;
			R_Rotate(T);
			break;
		 case RH:
			Lr=L->rchild;
			switch(Lr->bf) {
				case LH: (*T)->bf=RH;
						 L->bf=EH;
						 break;
				case EH: (*T)->bf=L->bf=EH;
						 break;
				case RH: (*T)->bf=EH;
						 L->bf=LH;
						 break;
			}
			Lr->bf=EH;
			L_Rotate(&(*T)->lchild);
			R_Rotate(T);
	}
}

void RightBalance(BiTree *T) {
	BiTree R,Rl;
	R=(*T)->rchild;
	switch(R->bf) {
	 case RH:
			  (*T)->bf=R->bf=EH;
			  L_Rotate(T);
			  break;
	 case LH:
			  Rl=R->lchild;
			  switch(Rl->bf) {
				case RH: (*T)->bf=LH;
						 R->bf=EH;
						 break;
				case EH: (*T)->bf=R->bf=EH;
						 break;
				case LH: (*T)->bf=EH;
						 R->bf=RH;
						 break;
			  }
			  Rl->bf=EH;
			  R_Rotate(&(*T)->rchild);
			  L_Rotate(T);
	}
}

Status InsertAVL(BiTree *T,char* e,Status *taller)
{
	if(!*T) {
		 *T=(BiTree)malloc(sizeof(BiTNode));
		 (*T)->data=e;
         (*T)->lchild=(*T)->rchild=NULL;
         (*T)->bf=EH;
		 *taller=TRUE;
	}
	else {
		if (strcmp(e, (*T)->data) == 0) {
			*taller=FALSE;
            return FALSE;
		}
		if (strcmp(e, (*T)->data) < 0) {
			if(!InsertAVL(&(*T)->lchild,e,taller))
				return FALSE;
			if(*taller)
				switch((*T)->bf) {
					case LH:
							LeftBalance(T);
                            *taller=FALSE;
                            break;
					case EH:
							(*T)->bf=LH;
                            *taller=TRUE;
                            break;
					case RH:
							(*T)->bf=EH;
                            *taller=FALSE;
                            break;
				}
		} else {
			if(!InsertAVL(&(*T)->rchild,e,taller))
				return FALSE;
			if(*taller)
				switch((*T)->bf) {
					case LH:
							(*T)->bf=EH;
                            *taller=FALSE;
                            break;
					case EH:
							(*T)->bf=RH;
                            *taller=TRUE;
                            break;
					case RH:
							RightBalance(T);
                            *taller=FALSE;
                            break;
				}
		}
	}
	return TRUE;
}
