using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Player : MonoBehaviour
{

    private int playerSpeed = 5;

    public static float MIN_SPEED = 15;
    public static int level;
    private float timeSlow;
    public static float speed;
    public bool isGrounded;
    public static bool isSlow;
    private float position;
    private float moveX;
    private Rigidbody rb;
    private Camera c;
    public GameObject block;

    public static bool hasStarted = true;
    private int spawnCount = 0;
    private float spawnTime = 0;
    private int buildingCount = 0;
    private float buildingTime = 0;
    Renderer rend;
    Color timeColor;
    public TextMesh text;
    public static bool isStrong;
    private float timeStrong;

    // Use this for initialization
    void Start()
    {
        Block.count = 0;
        c = Camera.main;
        rb = gameObject.GetComponent<Rigidbody>();
        level = 1;
        speed = MIN_SPEED;
        timeSlow = 0;
        timeStrong = 0;
        buildingTime = Time.fixedUnscaledTime;
        spawnTime = Time.fixedUnscaledTime;
        rend = GameObject.FindGameObjectWithTag("SlowBar").GetComponent<Renderer>();
        timeColor = rend.material.color;
        text = GameObject.FindGameObjectWithTag("SlowText").GetComponent<TextMesh>();
    }

    // Update is called once per frame
    void FixedUpdate()
    {
        if(Time.fixedUnscaledTime - spawnTime >= 0.05 && spawnCount < 50)
        {
            spawnTime = Time.fixedUnscaledTime;
            spawnCount++;
            Instantiate(GameObject.FindGameObjectWithTag("Respawn"));
        }

        if (Time.fixedUnscaledTime - buildingTime >= 0.15 && buildingCount < 85)
        {
            buildingTime = Time.fixedUnscaledTime;
            buildingCount++;
            Instantiate(GameObject.FindGameObjectWithTag("Building"));
        }

        if (Block.count > 3 * level)
        {
            Block.count = 0;
            speed = MIN_SPEED + level % 5;
            level++;
            Debug.Log("Level: " + level);
            Instantiate(GameObject.FindGameObjectWithTag("Finish"));
            Text.Initialize();
        }

        

        if (!isGrounded)
        {
            Physics.gravity = new Vector3(-26 * position * rb.velocity.y, -20 - 0.8f * System.Math.Abs(position), 0);
            c.transform.rotation = new Quaternion(c.transform.rotation.x, c.transform.rotation.y, position / 12, 1);
        }
        else
        {
            c.transform.rotation = new Quaternion(c.transform.rotation.x, c.transform.rotation.y, rb.position.x / 12, 1);

        }
        moveX = Input.GetAxis("Horizontal");
        if(System.Math.Abs(rb.position.x) >= 2.2f)
        {
            moveX = 0;
            rb.AddForce(rb.position.x > 0 ? -150 : 150, -200, 0);
        }

        if (Input.GetButtonDown("Jump"))
        {
            Jump();
        }
        if(Input.GetKeyDown(KeyCode.Q) && !isSlow && Time.unscaledTime - timeSlow >= 4) 
        {
            Slow();
        }
        if(Time.unscaledTime - timeSlow >= 2 && isSlow)
        {
            isSlow = false;
            speed *= 2;
            timeSlow = Time.unscaledTime;
        }

        if (Input.GetKeyDown(KeyCode.E) && !isStrong && Time.unscaledTime - timeStrong >= 4)
        {
            isStrong = true;
            timeStrong = Time.unscaledTime;
            Debug.Log("is strong");
        }
        if (Time.unscaledTime - timeStrong >= 2 && isStrong)
        {
            isStrong = false;
            timeStrong = Time.unscaledTime;
        }

        if (isSlow)
        {
            block.transform.localScale = new Vector3(1 - (Time.unscaledTime - timeSlow) / 2, block.transform.localScale.y, block.transform.localScale.z);
        }
        else
        {
            block.transform.localScale = new Vector3(Mathf.Min((Time.unscaledTime - timeSlow) / 4, 1), block.transform.localScale.y, block.transform.localScale.z);
        }
        if(block.transform.localScale.x == 1)
        {
            text.text = "Slow Ready";
        }
        else
        {
            text.text = "";
        }

        rb.velocity = new Vector3(moveX * playerSpeed, rb.velocity.y, 0);
    }

    void Slow()
    {
        timeSlow = Time.unscaledTime;
        speed /= 2;
        isSlow = true;
    }

    void Jump()
    {
        if(isGrounded)
        {
        playerSpeed = 0;
        position = rb.position.x;
        isGrounded = false;
        Physics.gravity = new Vector3(-25 * position * rb.velocity.y, -20 - 0.5f * System.Math.Abs(position), 0);
        rb.velocity = new Vector3(0, 8, 0);
        }
    }

    private void OnCollisionEnter(Collision collision)
    {
        Physics.gravity = new Vector3(5 * rb.position.x, -20, 0);
        isGrounded = true;
        playerSpeed = 8;

    }

    private void OnTriggerEnter(Collider other)
    {
        if(isStrong && other.tag == "Finish")
        {
            other.GetComponent<Rigidbody>().velocity = new Vector3(Random.RandomRange(-5, 5), 10, 10);
            other.GetComponent<Rigidbody>().useGravity = true;
        }
        else
        {
            SceneManager.LoadScene("SampleScene");
        }
    }

}
