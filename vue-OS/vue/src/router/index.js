import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/Layout.vue'
import Layout2 from '../layout/Layout2.vue'


const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: '/home',
    children: [
      {
        path: '/home',
        name: 'Home',
        component: () => import("../views/Home")
      },
      {
        path: '/news',
        name: 'News',
        component: () => import("../views/News")
      },
      {
        path: '/tiku',
        name: 'Tiku',
        component: () => import("../views/Tiku")
      },
      {
        path: '/person',
        name: 'Person',
        component: Layout2,
        children: [
          {
            path: '/person',
            name: 'Person',
            component: () => import("../views/Person")
          },
          {
            path: '/personData',
            name: 'PersonData',
            component: () => import("../views/PersonData")
          },
          {
            path: '/questionChoice',
            name: 'QuestionChoice',
            component: () => import("../views/QuestionChoice")
          },
          {
            path: '/questionJudge',
            name: 'QuestionJudge',
            component: () => import("../views/QuestionJudge")
          },
          {
            path: '/questionFill',
            name: 'QuestionFill',
            component: () => import("../views/QuestionFill")
          },
          {
            path: '/questionShort',
            name: 'QuestionShort',
            component: () => import("../views/QuestionShort")
          },
          {
            path: '/questionApplication',
            name: 'QuestionApplication',
            component: () => import("../views/QuestionApplication")
          },
          {
            path: '/comment',
            name: 'Comment',
            component: () => import("../views/Comment")
          },
          {
            path: '/newsManage',
            name: 'NewsManage',
            component: () => import("../views/NewsManage")
          },
          {
            path: '/lunboManage',
            name: 'LunboManage',
            component: () => import("../views/LunboManage")
          },
          {
            path: '/testManage',
            name: 'TestManage',
            component: () => import("../views/TestManage")
          },
        ]
      },
      {
        path: '/choice/choiceQuestion=:id',
        name: 'ChoiceQuestion',
        component: () => import("../views/ChoiceQuestion")
      },
      {
        path: '/choice',
        name: 'Choice',
        component: () => import("../views/Choice")
      },
      {
        path: '/judge/judgeQuestion=:id',
        name: 'JudgeQuestion',
        component: () => import("../views/JudgeQuestion")
      },
      {
        path: '/judge',
        name: 'Judge',
        component: () => import("../views/Judge")
      },
      {
        path: '/fill',
        name: 'Fill',
        component: () => import("../views/Fill")
      },
      {
        path: '/fill/fillQuestion=:id',
        name: 'FillQuestion',
        component: () => import("../views/FillQuestion")
      },
      {
        path: '/short',
        name: 'Short',
        component: () => import("../views/Short")
      },
      {
        path: '/short/shortQuestion=:id',
        name: 'ShortlQuestion',
        component: () => import("../views/ShortQuestion")
      },
      {
        path: '/application',
        name: 'Application',
        component: () => import("../views/Application")
      },
      {
        path: '/application/applicationQuestion=:id',
        name: 'ApplicationQuestion',
        component: () => import("../views/ApplicationQuestion")
      },
      {
        path: '/chapter',
        name: 'Chapter',
        component: () => import("../views/Chapter")
      },
      {
        path: '/random',
        name: 'Random',
        component: () => import("../views/Random")
      },
      {
        path: '/search',
        name: 'Search',
        component: () => import("../views/Search")
      },
      {
        path: '/article',
        name: 'Article',
        component: () => import("../views/Article")
      },
      {
        path: '/news/article/one',
        name: 'ArticleInfo',
        component: () => import("../views/ArticleInfo")
      },
      {
        path: '/userInfo',
        name: 'UserInfo',
        component: () => import("../views/UserInfo")
      },
    ]
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/home'
  },
  {
    path: '/test',
    name: 'test',
    component: () => import("../views/test")
  },
 ]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
