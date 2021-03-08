--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: choice; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.choice (
    id integer NOT NULL,
    answer integer,
    question_id integer,
    user_id integer
);


ALTER TABLE public.choice OWNER TO postgres;

--
-- Name: groups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.groups (
    id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.groups OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: option; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.option (
    question_id integer NOT NULL,
    description character varying(255),
    number integer NOT NULL
);


ALTER TABLE public.option OWNER TO postgres;

--
-- Name: question; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.question (
    id integer NOT NULL,
    answer_number integer,
    text character varying(5000),
    quiz_id integer
);


ALTER TABLE public.question OWNER TO postgres;

--
-- Name: quiz; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.quiz (
    id integer NOT NULL,
    name character varying(255),
    tag character varying(255),
    text character varying(2048),
    user_id integer
);


ALTER TABLE public.quiz OWNER TO postgres;

--
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.role OWNER TO postgres;

--
-- Name: score; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.score (
    id integer NOT NULL,
    score integer NOT NULL,
    quiz_id integer,
    user_id integer
);


ALTER TABLE public.score OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    interests character varying(255),
    name character varying(255),
    password character varying(255),
    surname character varying(255),
    username character varying(255),
    group_id integer,
    role_id integer
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Data for Name: choice; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.choice (id, answer, question_id, user_id) FROM stdin;
\.


--
-- Data for Name: groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.groups (id, name) FROM stdin;
1	SE-1905
\.


--
-- Data for Name: option; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.option (question_id, description, number) FROM stdin;
\.


--
-- Data for Name: question; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.question (id, answer_number, text, quiz_id) FROM stdin;
\.


--
-- Data for Name: quiz; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.quiz (id, name, tag, text, user_id) FROM stdin;
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role (id, name) FROM stdin;
1	USER
2	ADMIN
\.


--
-- Data for Name: score; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.score (id, score, quiz_id, user_id) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, interests, name, password, surname, username, group_id, role_id) FROM stdin;
1	admin	admin	$2a$10$kS0P96I9sPgkN7Q002v6Ruz3k.TwkclBwKVnPJCMYMFQ0K.N7rnMC	admin	admin	1	2
2	user	user	$2a$10$68c8XIHaukm.FlEGP8SJt.k3BZor4L20Wx2ebF2LpXHOtcjQ0RJR2	user	user	1	1
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 2, true);


--
-- Name: choice choice_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.choice
    ADD CONSTRAINT choice_pkey PRIMARY KEY (id);


--
-- Name: groups groups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (id);


--
-- Name: option option_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.option
    ADD CONSTRAINT option_pkey PRIMARY KEY (question_id, number);


--
-- Name: question question_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question
    ADD CONSTRAINT question_pkey PRIMARY KEY (id);


--
-- Name: quiz quiz_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quiz
    ADD CONSTRAINT quiz_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: score score_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.score
    ADD CONSTRAINT score_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: users fk4qu1gr772nnf6ve5af002rwya; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk4qu1gr772nnf6ve5af002rwya FOREIGN KEY (role_id) REFERENCES public.role(id);


--
-- Name: question fkb0yh0c1qaxfwlcnwo9dms2txf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question
    ADD CONSTRAINT fkb0yh0c1qaxfwlcnwo9dms2txf FOREIGN KEY (quiz_id) REFERENCES public.quiz(id);


--
-- Name: choice fkcaq6r76cswke5b9fk6fyx3y5w; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.choice
    ADD CONSTRAINT fkcaq6r76cswke5b9fk6fyx3y5w FOREIGN KEY (question_id) REFERENCES public.question(id);


--
-- Name: users fkemfuglprp85bh5xwhfm898ysc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fkemfuglprp85bh5xwhfm898ysc FOREIGN KEY (group_id) REFERENCES public.groups(id);


--
-- Name: choice fkf2507a71f9mpopvmf1pbrs4x1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.choice
    ADD CONSTRAINT fkf2507a71f9mpopvmf1pbrs4x1 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: option fkgtlhwmagte7l2ssfsgw47x9ka; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.option
    ADD CONSTRAINT fkgtlhwmagte7l2ssfsgw47x9ka FOREIGN KEY (question_id) REFERENCES public.question(id);


--
-- Name: score fkjmvhce8vfl803xnw4cdxjw1u1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.score
    ADD CONSTRAINT fkjmvhce8vfl803xnw4cdxjw1u1 FOREIGN KEY (quiz_id) REFERENCES public.quiz(id);


--
-- Name: quiz fkoj71coq74ryc5c9c9o482p8mm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quiz
    ADD CONSTRAINT fkoj71coq74ryc5c9c9o482p8mm FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: score fkpqss47h2fevnmkh76r14055o0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.score
    ADD CONSTRAINT fkpqss47h2fevnmkh76r14055o0 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- PostgreSQL database dump complete
--

