#include "list.h"

#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

// Initialize a list
void list_init(my_list_t* list) {
  list->head = NULL;
  pthread_mutex_init(&list->m, NULL);
}

// Destroy a list
void list_destroy(my_list_t* list) {
  node_t* cur = list->head;
  node_t* next = NULL;
  while(cur != NULL) {
    next = cur->next;
    free(cur->key);
    free(cur);
    cur = next;
  }
}

// Push an element onto a list
void list_insert(my_list_t* list, const char* key, int value) {
  pthread_mutex_lock(&list->m);
  node_t* cur = list->head;
  while(cur != NULL) {
    if(strcmp(key, cur->key) == 0) {
      cur->value = value;
      pthread_mutex_unlock(&list->m);
      return;
    }
    cur = cur->next;
  }
  node_t* temp = malloc(sizeof(node_t));
  if(temp == NULL) {
    fprintf(stderr, "Malloc failed!\n");
    list_destroy(list);
    exit(1);
  }
  temp->key = strdup(key);
  temp->value = value;
  temp->next = list->head;
  list->head = temp;
  pthread_mutex_unlock(&list->m);
}

// Get the value of the corresponding key
int list_lookup(my_list_t* list, const char* key) {
  pthread_mutex_lock(&list->m);
  node_t* cur = list->head;
  while(cur != NULL) {
    if(strcmp(key, cur->key) == 0) {
      int val = cur->value;
      pthread_mutex_unlock(&list->m);
      return val;
    }
    cur = cur->next;
  }
  pthread_mutex_unlock(&list->m);
  return -1;  
}

// Pop an element off of a list
int list_remove(my_list_t* list, const char* key) {
  pthread_mutex_lock(&list->m);
  node_t* prev = NULL;
  node_t* cur = list->head;
  // The first element is the one we want to remove
  if(strcmp(key, cur->key) == 0) {
    list->head = cur->next;
    int val = cur->value;
    free(cur->key);
    free(cur);
    pthread_mutex_unlock(&list->m);
    return val;
  } else {
    prev = cur;
    cur = cur->next;
  }
  
  while(cur != NULL) {
    if(strcmp(key, cur->key) == 0) {
      prev->next = cur->next;
      int val = cur->value;
      free(cur->key);
      free(cur);
      pthread_mutex_unlock(&list->m);
      return val;
    }
    prev = cur;
    cur = cur->next;
  }
  pthread_mutex_unlock(&list->m);
  return -1; 
}
